package com.lewis.msemployee.entities.models;

import org.springframework.lang.Nullable;

import javax.validation.constraints.Null;

public class PageModel {


    @Nullable
    private Integer pagNumber;
    @Nullable
    private Integer pagSize;
    @Nullable
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
