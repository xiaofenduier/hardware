package com.sunshine.hardware.model.request;


import com.sunshine.hardware.model.BraceletData;

public class BraceletDataRequest extends BraceletData {

    private Long beginTime;
    private Long endTime;
    private String probeMac;
    private String braceletMac;

    public BraceletDataRequest() {
    }

    public BraceletDataRequest(Long beginTime, Long endTime, String probeMac) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.probeMac = probeMac;
    }

    public BraceletDataRequest(Long beginTime, Long endTime) {
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    @Override
    public String getBraceletMac() {
        return braceletMac;
    }

    @Override
    public void setBraceletMac(String braceletMac) {
        this.braceletMac = braceletMac;
    }

    public Long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    @Override
    public String getProbeMac() {
        return probeMac;
    }

    @Override
    public void setProbeMac(String probeMac) {
        this.probeMac = probeMac;
    }
}
