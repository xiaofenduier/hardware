package com.sunshine.hardware.model;

import com.sunshine.hardware.util.Page;

/**
 *手环数据
 * @author lyc
 *
 */
public class BraceletData extends Page {

    private int id;
    //手环mac
    private String braceletMac;
    //心率
    private int heartRate;
    //步数
    private int step;
    //活动状态
    private String active;
    //睡眠状态
    private String sleep;
    //跳绳模式
    private Integer skipModel;
    //跳绳数量
    private int skipNum;
    //跳绳时长
    private int skipTime;
    //hrv
    private String version;
    //上传数据utc
    private Long utc;
    //静止心率
    private int staticHeartRate;
    //探针mac
    private String probeMac;
    //uuid
    private String uuid;
    //添加时间
    private Long addTime;
    //点亮
    private int battery;

    private Integer signalValue;

    private Long startTime;
    private Long endTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public String getBraceletMac() {
        return braceletMac;
    }

    public void setBraceletMac(String braceletMac) {
        this.braceletMac = braceletMac;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getSleep() {
        return sleep;
    }

    public void setSleep(String sleep) {
        this.sleep = sleep;
    }

    public Integer getSkipModel() {
        return skipModel;
    }

    public void setSkipModel(Integer skipModel) {
        this.skipModel = skipModel;
    }

    public int getSkipNum() {
        return skipNum;
    }

    public void setSkipNum(int skipNum) {
        this.skipNum = skipNum;
    }

    public int getSkipTime() {
        return skipTime;
    }

    public void setSkipTime(int skipTime) {
        this.skipTime = skipTime;
    }

    public Long getUtc() {
        return utc;
    }

    public void setUtc(Long utc) {
        this.utc = utc;
    }

    public int getStaticHeartRate() {
        return staticHeartRate;
    }

    public void setStaticHeartRate(int staticHeartRate) {
        this.staticHeartRate = staticHeartRate;
    }

    public String getProbeMac() {
        return probeMac;
    }

    public void setProbeMac(String probeMac) {
        this.probeMac = probeMac;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public Integer getSignalValue() {
        return signalValue;
    }

    public void setSignalValue(Integer signalValue) {
        this.signalValue = signalValue;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
