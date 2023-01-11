package com.lewis.msemployee.entities.models;

import javax.validation.constraints.Null;

public class PageModel {


    @Null
    private Integer pagNumber;
    @Null
    private Integer pagSize;
    @Null
    private String sortBy;

    public PageModel(Integer pagNumber, Integer pagSize, String sortBy) {
        this.pagNumber = pagNumber;
        this.pagSize = pagSize;
        this.sortBy = sortBy;
    }

    public Integer getPagNumber() {
        return pagNumber;
    }

    public void setPagNumber(Integer pagNumber) {
        this.pagNumber = pagNumber;
    }

    public Integer getPagSize() {
        return pagSize;
    }

    public void setPagSize(Integer pagSize) {
        this.pagSize = pagSize;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
