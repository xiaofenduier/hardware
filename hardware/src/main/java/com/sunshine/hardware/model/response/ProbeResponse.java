package com.sunshine.hardware.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sunshine.hardware.model.Probe;

import java.util.List;
import java.util.Map;

@JsonInclude(value = JsonInclude.Include.NON_NULL, content = JsonInclude.Include.NON_NULL)
public class ProbeResponse extends Probe {

    //探针列表
    private List<ProbeResponse> rows;
    //数据条数
    private long total;

    private String statusStr;

    private String isNormalStr;

    private String onLineStr;

    private List<String> failList;

    private List<List<String>> throughoutList;

    private List<String> probeMacList;

    private List<Long> probeThroughtoutList;

    private String location;

    private String probeMac;

    @Override
    public String getProbeMac() {
        return probeMac;
    }

    @Override
    public void setProbeMac(String probeMac) {
        this.probeMac = probeMac;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    public String getOnLineStr() {
        return onLineStr;
    }

    public void setOnLineStr(String onLineStr) {
        this.onLineStr = onLineStr;
    }

    public List<Long> getProbeThroughtoutList() {
        return probeThroughtoutList;
    }

    public void setProbeThroughtoutList(List<Long> probeThroughtoutList) {
        this.probeThroughtoutList = probeThroughtoutList;
    }

    private List<Map<String, Object>> probeValueList;

    public List<Map<String, Object>> getProbeValueList() {
        return probeValueList;
    }

    public void setProbeValueList(List<Map<String, Object>> probeValueList) {
        this.probeValueList = probeValueList;
    }

    public List<String> getProbeMacList() {
        return probeMacList;
    }

    public void setProbeMacList(List<String> probeMacList) {
        this.probeMacList = probeMacList;
    }

    private String regularTime;

    public String getRegularTime() {
        return regularTime;
    }

    public void setRegularTime(String regularTime) {
        this.regularTime = regularTime;
    }

    public List<List<String>> getThroughoutList() {
        return throughoutList;
    }

    public void setThroughoutList(List<List<String>> throughoutList) {
        this.throughoutList = throughoutList;
    }

    public List<String> getFailList() {
        return failList;
    }

    public void setFailList(List<String> failList) {
        this.failList = failList;
    }

    public List<String> getSuccessList() {
        return successList;
    }

    public void setSuccessList(List<String> successList) {
        this.successList = successList;
    }

    private List<String> successList;

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getIsNormalStr() {
        return isNormalStr;
    }

    public void setIsNormalStr(String isNormalStr) {
        this.isNormalStr = isNormalStr;
    }

    public List<ProbeResponse> getRows() {
        return rows;
    }

    public void setRows(List<ProbeResponse> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
