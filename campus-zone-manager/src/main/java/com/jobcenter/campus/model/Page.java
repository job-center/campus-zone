package com.jobcenter.campus.model;

import java.util.List;

/**
 * Created by xiayun on 25/3/17.
 */
public class Page<T> {
    public static final int DEFAULT_PAGE_NUM = 1;  //当前页数(缺省第1页)
    public static final int DEFAULT_PAGE_SIZE = 10;    //每页记录数(缺省10条)

    //查询参数
    private int pageNum = DEFAULT_PAGE_NUM;    //当前页数
    private int pageSize = DEFAULT_PAGE_SIZE;   //每页记录数

    //查询结果值
    private int total;     //总记录数
    private List<T> result; //分页记录集

    /**
     * 构造分页对象
     * @param pageNum
     * @param pageSize
     */
    public Page(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
//        this.startRow = pageNum > 0 ? (pageNum - 1) * pageSize : 0;
//        this.endRow = pageNum * pageSize;
    }

    /**
     * 构造分页对象
     * @param pageNum 页号
     * @param pageSize 每页条数
     * @param total 返回总数
     */
    public Page(int pageNum, int pageSize, int total) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
    }

    public Page(Page page,List<T> result) {
        this(page.getPageNum(), page.getPageSize(), page.getTotal());
        this.result = result;
    }

    public Page(){
        this.pageNum = DEFAULT_PAGE_NUM;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", result=" + result +"}";
    }
}
