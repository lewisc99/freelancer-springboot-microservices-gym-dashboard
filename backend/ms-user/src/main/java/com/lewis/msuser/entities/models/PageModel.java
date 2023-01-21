package com.lewis.msuser.entities.models;

import org.springframework.lang.Nullable;

public class PageModel {
    @Nullable
    private  int pagNumber;
    @Nullable
    private int pagSize;
    @Nullable
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
