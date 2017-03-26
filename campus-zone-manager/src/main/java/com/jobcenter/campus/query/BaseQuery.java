package com.jobcenter.campus.query;

import com.jobcenter.campus.common.common.BaseEntity;
import com.jobcenter.campus.model.Page;

import java.util.Set;

/**
 * Created by xiayun on 26/3/17.
 */
public class BaseQuery extends BaseEntity {
    /**
     * 按照id查詢
     */
    private Integer id;
    /**
     * id列表查詢
     */
    private Set<Integer> ids;
    /**
     * 当前页数
     */
    private Integer pageNum = Page.DEFAULT_PAGE_NUM;
    /**
     * 每页条数
     */
    private Integer pageSize = Page.DEFAULT_PAGE_SIZE;
    /**
     * 排序组合，id desc
     */
    private String oderByClause;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Integer> getIds() {
        return ids;
    }

    public void setIds(Set<Integer> ids) {
        this.ids = ids;
    }

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

    public String getOderByClause() {
        return oderByClause;
    }

    public void setOderByClause(String oderByClause) {
        this.oderByClause = oderByClause;
    }
}
