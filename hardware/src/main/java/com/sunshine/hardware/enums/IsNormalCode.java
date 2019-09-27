package com.sunshine.hardware.enums;

public enum IsNormalCode {
    REGULAR(0, "regular"),
    HEIGHT(1, "height"),
    LOW(2, "low");

    private int id;
    private String isMormal;

    IsNormalCode(int id, String isMormal) {
        this.id = id;
        this.isMormal = isMormal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsMormal() {
        return isMormal;
    }

    public void setIsMormal(String isMormal) {
        this.isMormal = isMormal;
    }
}
