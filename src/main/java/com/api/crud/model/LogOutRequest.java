package com.api.crud.model;

public class LogOutRequest {
    private String userName;
    private String accessToken;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public LogOutRequest(String userName, String accessToken) {
        this.userName = userName;
        this.accessToken = accessToken;
    }
}
