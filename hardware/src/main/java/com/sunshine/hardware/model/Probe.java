package com.sunshine.hardware.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sunshine.hardware.util.Page;

@JsonInclude(value = JsonInclude.Include.NON_NULL, content = JsonInclude.Include.NON_NULL)
public class Probe extends Page {

    private int id;
    private String probeMac;
    //状态
    private Integer status;
    //位置
    private String location;
    //是否运行正常
    private Integer isNormal;
    //实时吞吐量
    private Long regularThroughput;

    private Integer onLine;

    private String onLineStr;

    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOnLineStr() {
        return onLineStr;
    }

    public void setOnLineStr(String onLineStr) {
        this.onLineStr = onLineStr;
    }

    public Integer getOnLine() {
        return onLine;
    }

    public void setOnLine(Integer onLine) {
        this.onLine = onLine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getIsNormal() {
        return isNormal;
    }

    public void setIsNormal(Integer isNormal) {
        this.isNormal = isNormal;
    }

    public String getProbeMac() {
        return probeMac;
    }

    public Long getRegularThroughput() {
        return regularThroughput;
    }

    public void setRegularThroughput(Long regularThroughput) {
        this.regularThroughput = regularThroughput;
    }

    public void setProbeMac(String probeMac) {
        this.probeMac = probeMac;
    }
}
