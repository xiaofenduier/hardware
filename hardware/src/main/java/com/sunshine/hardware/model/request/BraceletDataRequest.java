package com.sunshine.hardware.model.request;


import com.sunshine.hardware.model.BraceletData;

public class BraceletDataRequest extends BraceletData {

    private Long startTime;
    private Long endTime;
    private String probeMac;
    private String braceletMac;

    public BraceletDataRequest() {
    }

    public BraceletDataRequest(Long startTime, Long endTime, String probeMac) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.probeMac = probeMac;
    }

    public BraceletDataRequest(Long startTime, Long endTime) {
        this.startTime = startTime;
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

    @Override
    public Long getStartTime() {
        return startTime;
    }

    @Override
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    @Override
    public Long getEndTime() {
        return endTime;
    }

    @Override
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
