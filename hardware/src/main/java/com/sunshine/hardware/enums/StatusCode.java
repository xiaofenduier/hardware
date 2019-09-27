package com.sunshine.hardware.enums;

public enum StatusCode {

    NORMAL(0, "开启"),
    NOTNORMAL(1, "关闭"),
    WORK(3, "工作"),
    WAIT(4, "待机"),
    ONLINE(5, "在线"),
    OFFLINE(6, "不在线");
    private int id;
    private String status;

    StatusCode(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
