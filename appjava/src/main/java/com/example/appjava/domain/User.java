package com.example.appjava.domain;

import com.example.appjava.annonation.StringParamAnnonation;

public class User {
    @StringParamAnnonation(canBeNull = false)
    private String userName;
    private int id;
    @StringParamAnnonation(canBeNull = false)
    private String password;
    private String token;
    private Long loginTime;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
