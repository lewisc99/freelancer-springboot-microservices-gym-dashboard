package com.lewis.msuser.entities.models;

public class pageModel {

    private  int pagNumber;
    private int pagSize;
    private String sortBy;

    public int getPagNumber() {
        return pagNumber;
    }

    public void setPagNumber(int pagNumber) {
        this.pagNumber = pagNumber;
    }

    public int getPagSize() {
        return pagSize;
    }

    public void setPagSize(int pagSize) {
        this.pagSize = pagSize;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
