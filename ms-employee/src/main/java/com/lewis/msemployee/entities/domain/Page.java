package com.lewis.msemployee.entities.domain;

public class Page {

    private int size;
    private int totalElements;
    private int totalPages;
    private int pagNumber;

    public Page(){}

    public Page(int size, int totalElements, int totalPages, int pagNumber) {
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.pagNumber = pagNumber;
    }

    public Page(int size, int pagNumber) {
        this.size = size;
        this.pagNumber = pagNumber;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPagNumber() {
        return pagNumber;
    }

    public void setPagNumber(int pagNumber) {
        this.pagNumber = pagNumber;
    }
}
