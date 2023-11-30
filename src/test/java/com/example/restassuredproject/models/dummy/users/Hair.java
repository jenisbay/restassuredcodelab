package com.example.restassuredproject.models.dummy.users;

public class Hair{
    public String color;
    public String type;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Hair{" +
                "color='" + color + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

