package com.example.restassuredproject.models;

public class SuccessLoginResponse {
    private String token;

    public SuccessLoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
