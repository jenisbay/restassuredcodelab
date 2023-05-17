package com.example.restassuredproject.models;

import java.util.Date;

public class UserResponse {

    public String name;
    public String job;
    public String id;
    public String createdAt;

    public UserResponse(String name, String job, String id, String createdAt) {
        this.name = name;
        this.job = job;
        this.id = id;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
