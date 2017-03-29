package com.jobcenter.campus.domin.page;

import com.google.common.collect.Maps;
import com.jobcenter.campus.common.common.BaseEntity;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Map;

/**
 * 查询请求类，请求参数放入queryData中
 * Created by xiayun on 28/3/17.
 */
public class Seed extends BaseEntity {

    public static final String PAGE_NUMBER		= "page";
    public static final String PAGE_SIZE		= "pagesize";
    public static final String ORDER		    = "order";

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private String orderBy;

    private Map<String,String> queryData = Maps.newHashMap();

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Map<String, String> getQueryData() {
        return queryData;
    }

    public void setQueryData(Map<String, String> queryData) {
        this.queryData = queryData;
    }

    public String getString(String name) {
        if (queryData.containsKey(name)) {
            return queryData.get(name);
        }
        return null;
    }

    public Integer getInteger(String name) {
        if (queryData.containsKey(name) && NumberUtils.isNumber(queryData.get(name))) {
            return NumberUtils.toInt(queryData.get(name));
        }
        return null;
    }

    public Long getLong(String name) {
        if (queryData.containsKey(name) && NumberUtils.isNumber(queryData.get(name))) {
            return NumberUtils.toLong(queryData.get(name));
        }
        return null;
    }

    public Double getDouble(String name) {
        if (queryData.containsKey(name) && NumberUtils.isNumber(queryData.get(name))) {
            return NumberUtils.toDouble(queryData.get(name));
        }
        return null;
    }
}
