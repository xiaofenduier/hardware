package com.sunshine.hardware.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sunshine.hardware.model.BraceletData;

import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_NULL, content = JsonInclude.Include.NON_NULL)
public class BraceletDataResponse extends BraceletData {

    //探针列表
    private List<BraceletData> rows;

    public List<BraceletData> getRows() {
        return rows;
    }

    public void setRows(List<BraceletData> rows) {
        this.rows = rows;
    }

    //数据条数
    private long total;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    private double location;

    public double getLocation() {
        return location;
    }

    public void setLocation(double location) {
        this.location = location;
    }
}
