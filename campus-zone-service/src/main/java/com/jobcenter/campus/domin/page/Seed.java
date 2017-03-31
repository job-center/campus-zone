package com.jobcenter.campus.domin.page;

import com.google.common.collect.Maps;
import com.jobcenter.campus.common.common.BaseEntity;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 查询请求类，请求参数放入queryData中
 * Created by xiayun on 28/3/17.
 */
public class Seed<T> extends BaseEntity {

    public static final String PAGE_NUMBER		= "page";
    public static final String PAGE_SIZE		= "pagesize";
    public static final String ORDER		    = "order";

    private Integer pageNumber = 1;

    private Integer pageSize = 10;

    private String orderBy;

    private Integer totalSize = 0;

    private List<T> result = new ArrayList<T>();

    private String actionPath = StringUtils.EMPTY;

    private String queryStr = StringUtils.EMPTY;

    private Map<String,String> queryData = Maps.newHashMap();

    /**
     *  The page number attribute is the current page number.
     */
    public Integer getPageNumber() {
        return this.pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
        if (pageNumber < 1) {
            this.pageNumber = 1;
        }
    }

    /**
     * The page size per page
     */
    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        if (pageSize < 1) {
            this.pageSize = 10;
        }
    }

    /**
     * The result list will be set into a model for generate datatable html code to client.
     */
    public List<T> getResult() {
        return this.result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    /**
     * The total size is the size of all records are satisfied with filters.
     */
    public Integer getTotalSize() {
        return this.totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
        if (getTotalPages() > 0 && this.pageNumber > getTotalPages()) {
            this.pageNumber = getTotalPages().intValue();
        }
    }

    /**
     * Dedicates the size of all records
     */
    public Long getTotalPages() {
        if (totalSize < 0) {
            return 0L;
        }
        long count = totalSize / pageSize;
        if (totalSize % pageSize > 0) {
            count++;
        }
        return count;
    }

    /**
     * Dedicates the start position for fetch records from Database
     */
    public Integer getStartPosition() {
        return (pageNumber - 1) * pageSize;
    }

    /**
     * Dedicates the end position for fetch records from Database
     */
    public Integer getEndPosition() {
        int count = pageNumber * pageSize;
        if (getTotalSize() > 0 && getTotalSize() < pageSize) {
            count = getTotalSize().intValue();
        }
        return count;
    }

    /**
     * The first page number should be 1
     */
    public Boolean getIsFirstPage() {
        return pageNumber == 1;
    }

    /**
     * The last page number should be total pages size
     */
    public Boolean getIsLastPage() {
        return pageNumber == getTotalPages().longValue();
    }

    /**
     * Dedicates if it has previous page
     */
    public Boolean getHasPreviousPage() {
        return pageNumber - 1 >= 1;
    }

    /**
     * Dedicates if it has next page
     */
    public Boolean getHasNextPage() {
        return pageNumber + 1 <= getTotalPages();
    }

    /**
     * The action path is the current page URL.
     * <p>URL is a relative path, like '/products'.</p>
     */
    public String getActionPath() {
        return actionPath;
    }

    public void setActionPath(String actionPath) {
        this.actionPath = actionPath;
    }

    /**
     * Return query string from filters
     */
    public String getQueryStr() {
        return queryStr;
    }

    public boolean isPageable() {
        return pageNumber != null && pageSize != null;
    }

    public Integer getPage(){
        return this.getPageNumber();
    }

    public void setPage(Integer page){
        this.setPageNumber(page);
    }

    public void setQueryData(Map<String, String> queryData) {
        this.queryData = queryData;

        StringBuilder queryStringList = new StringBuilder();
        for(String key : queryData.keySet()){
            String value = queryData.get(key);
            if(!value.equals(StringUtils.EMPTY)){
                queryStringList.append("&" + key + "=" + value );
            }
        }
        this.queryStr = queryStringList.toString();
    }

    public Map<String, String> getQueryData() {
        return queryData;
    }

    public String getString(String name) {
        if (MapUtils.isNotEmpty(queryData) && queryData.containsKey(name)) {
            return queryData.get(name);
        }
        return null;
    }

    public Integer getInteger(String name) {
        if (MapUtils.isNotEmpty(queryData) && queryData.containsKey(name) && NumberUtils.isNumber(queryData.get(name))) {
            return NumberUtils.toInt(queryData.get(name));
        }
        return null;
    }

    public Long getLong(String name) {
        if (MapUtils.isNotEmpty(queryData) && queryData.containsKey(name) && NumberUtils.isNumber(queryData.get(name))) {
            return NumberUtils.toLong(queryData.get(name));
        }
        return null;
    }

    public Double getDouble(String name) {
        if (MapUtils.isNotEmpty(queryData) && queryData.containsKey(name) && NumberUtils.isNumber(queryData.get(name))) {
            return NumberUtils.toDouble(queryData.get(name));
        }
        return null;
    }


}
