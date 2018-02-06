package com.example.model;

public class Paging {
    private int pageSize = 3;
    private int currentPage;
    private int totalPage;
    private long totalCount;

    public Paging() {
        super();
    }

    public Paging(int pageSize, int currentPage, int totalPage) {
        super();
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
