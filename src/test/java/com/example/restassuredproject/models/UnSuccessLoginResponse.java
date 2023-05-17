package com.example.restassuredproject.models;

public class UnSuccessLoginResponse {
    private String error;

    public UnSuccessLoginResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
