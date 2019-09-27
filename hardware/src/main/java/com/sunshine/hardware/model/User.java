package com.sunshine.hardware.model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * ProjectName: sunshineforce-hardware
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/9/19 18:03
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */

public class User {

    private Integer id;

    private String username;

    private String password;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
