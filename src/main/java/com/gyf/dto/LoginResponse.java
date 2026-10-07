package com.gyf.dto;

public class LoginResponse {

    private String token;
    private LoginUserResponse user;

    public LoginResponse() {
    }

    public LoginResponse(
            String token,
            LoginUserResponse user) {

        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginUserResponse getUser() {
        return user;
    }

    public void setUser(LoginUserResponse user) {
        this.user = user;
    }
}