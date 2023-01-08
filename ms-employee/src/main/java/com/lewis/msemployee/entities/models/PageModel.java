package com.lewis.msemployee.entities.models;

public class PageModel {

    private Integer pagNumber;
    private Integer pagSize;

    public PageModel(Integer pagNumber, Integer pagSize) {
        this.pagNumber = pagNumber;
        this.pagSize = pagSize;
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
}
