package com.jobcenter.campus.intercepter;

import com.jobcenter.campus.model.Page;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * Created by xiayun on 25/3/17.
 */
@Intercepts({@Signature(type = StatementHandler.class,method = "prepare",args = {Connection.class,Integer.class}),
            @Signature(type = ResultSetHandler.class,method = "handleResultSets",args = {Statement.class})})
public class PageMybtisIntercepter implements Interceptor {

    private static final ThreadLocal<Page> localPage = new ThreadLocal<>();

    private static final ThreadLocal<Boolean> loadTotal = new ThreadLocal(){//是否查询记录总数，默认为true;
        @Override
        protected Object initialValue() {
            return true;
        }
    };

    private static Logger logger = LoggerFactory.getLogger(PageMybtisIntercepter.class);

    /**
     * 分页开始
     *
     * @param pageNum
     * @param pageSize
     */
    public static void startPage(Integer pageNum,Integer pageSize){
        if (pageNum == null){
            pageNum = Page.DEFAULT_PAGE_NUM;
        }
        if (pageSize == null){
            pageSize = Page.DEFAULT_PAGE_SIZE;
        }
        Page page = new Page(pageNum,pageSize);
        localPage.set(page);
    }

    /**
     * 结束分页并返回结果，该方法必须被调用，否则localPage会一直保存下去，直到下一次startPage
     *
     * @return
     */
    public static Page endPage(){
        Page page = localPage.get();
        localPage.remove();
        return page;
    }

    /**
     * 设置是否需要获取分页的总数，默认是获取
     * @param loadTotalCount
     */
    public static void setLoadTotal(Boolean loadTotalCount){
        if (loadTotalCount == null){
            loadTotalCount = true;
        }
        loadTotal.set(loadTotalCount);
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (localPage.get() == null){
            return invocation.proceed();
        }
        if (invocation.getTarget() instanceof StatementHandler){
            StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
            MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
            // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环  
            // 可以分离出最原始的的目标类)  
            while (metaStatementHandler.hasGetter("h")) {
                Object object = metaStatementHandler.getValue("h");
                metaStatementHandler = SystemMetaObject.forObject(object);
            }
            // 分离最后一个代理对象的目标类  
            while (metaStatementHandler.hasGetter("target")) {
                Object object = metaStatementHandler.getValue("target");
                metaStatementHandler = SystemMetaObject.forObject(object);
            }
            MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
            //分页信息if (localPage.get() != null) {  
            Page page = localPage.get();
            BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
            // 分页参数作为参数对象parameterObject的一个属性  
            String sql = boundSql.getSql();
            // 重写sql  
            String pageSql = buildPageSql(sql, page);
            //重写分页sql  
            metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
            Connection connection = (Connection) invocation.getArgs()[0];
            if (loadTotal.get()) {
                // 重设分页参数里的总页数等
                setPageParameter(sql, connection, mappedStatement, boundSql, page);
            }
            // 将执行权交给下一个拦截器  
            return invocation.proceed();
        }else if (invocation.getTarget() instanceof ResultSetHandler){
            Object result = invocation.proceed();
            Page page = localPage.get();
            page.setResult((List) result);
            return result;
        }
        return  null;
    }

    /**
     * 修改原SQL为分页SQL
     * @param sql
     * @param page
     * @return
     */
    private String buildPageSql(String sql, Page page) {
        StringBuilder pageSql = new StringBuilder(200);

        //Oracle
//        pageSql.append("select * from ( select temp.*, rownum row_id from ( ");
//        pageSql.append(sql);
//        pageSql.append(" ) temp where rownum <= ").append(page.getEndRow());
//        pageSql.append(") where row_id > ").append(page.getStartRow());

        //Mysql
        pageSql.append(sql);
        int startRow = page.getPageNum() > 0 ? (page.getPageNum() - 1) * page.getPageSize() : 0;
        pageSql.append(" limit ").append(startRow).append(",").append(page.getPageSize());

        return pageSql.toString();
    }

    /**
     * 获取总记录数
     *
     * @param sql
     * @param connection
     * @param mappedStatement
     * @param boundSql
     * @param page
     */
    private void setPageParameter(String sql, Connection connection, MappedStatement mappedStatement, BoundSql boundSql, Page page) {
        // 记录总记录数
//        String countSql = "select count(0) from (" + sql + ") tableForCount";
        String presql = sql.replaceAll("\\n"," ");
        presql = presql.replaceAll(" [Ff][Rr][Oo][Mm] ", " from ");
        String countSql;
        if(!presql.contains(" distinct")){
            if(presql.indexOf(" from ")>0) {
                String newSql = "select 1 ";
                newSql += presql.substring(presql.indexOf(" from "));
                if (newSql.lastIndexOf(" order by ") > 0) {
                    newSql = newSql.substring(0, newSql.lastIndexOf(" order by "));
                }
                countSql = "select count(0) from (" + newSql + ") tableForCount";
            }else{
                countSql = "select count(0) from (" + sql + ") tableForCount";
            }
        }else{
            countSql = "select count(0) from (" + sql + ") tableForCount";
        }
        PreparedStatement countStmt = null;
        ResultSet rs = null;
        try {
            countStmt = connection.prepareStatement(countSql);
            BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql,
                    boundSql.getParameterMappings(), boundSql.getParameterObject());

            //设置countBS的AdditionalParameter（修复设置parameter时的Bug）
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            if (parameterMappings != null) {
                for (int i = 0; i < parameterMappings.size(); i++) {
                    ParameterMapping parameterMapping = parameterMappings.get(i);
                    if (parameterMapping.getMode() != ParameterMode.OUT) {
                        String propertyName = parameterMapping.getProperty();
                        if (boundSql.hasAdditionalParameter(propertyName)) {
                            countBS.setAdditionalParameter(propertyName, boundSql.getAdditionalParameter(propertyName));
                        }
                    }
                }
            }

            setParameters(countStmt, mappedStatement, countBS, boundSql.getParameterObject());
            rs = countStmt.executeQuery();
            int totalCount = 0;
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
            page.setTotal(totalCount);
        } catch (SQLException e) {
            logger.error("Ignore this exception", e);
        } finally {
            try {
                if(rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                logger.error("Ignore this exception", e);
            }
            try {
                countStmt.close();
            } catch (SQLException e) {
                logger.error("Ignore this exception", e);
            }
        }
    }

    /**
     * 代入参数值
     *
     * @param ps
     * @param mappedStatement
     * @param boundSql
     * @param parameterObject
     * @throws SQLException
     */
    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql,
                               Object parameterObject) throws SQLException {
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
        parameterHandler.setParameters(ps);
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler || target instanceof ResultSetHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
