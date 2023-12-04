package com.example.restassuredproject.models.dummy.todo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties()
public class Data {

    private String todo;

    private boolean completed;

    private int userId;

    public Data() {
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Data{" +
                "todo='" + todo + '\'' +
                ", completed=" + completed +
                ", userId=" + userId +
                '}';
    }
}
