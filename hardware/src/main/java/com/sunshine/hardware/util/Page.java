package com.sunshine.hardware.util;

import org.apache.commons.lang3.StringUtils;

public class Page {

    private int pageSize;

    private int currentPage;

    private String orderName;

    private String orderType;

    private int start;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if(pageSize == 0){
            pageSize = 10;
        }
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        if(currentPage == 0){
            currentPage = 1;
        }
        this.currentPage = currentPage;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        if(StringUtils.isEmpty(orderName)){
            orderName = "id";
        }
        this.orderName = orderName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        if(StringUtils.isEmpty(orderType)){
            orderType = "asc";
        }
        this.orderType = orderType;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
