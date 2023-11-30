package com.example.restassuredproject.models.dummy.users;

import java.util.List;

public class UsersResponseBody {
    public List<User> users;
    public int total;
    public int skip;
    public int limit;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "UsersResponseBody{" +
                "users=" + users +
                ", total=" + total +
                ", skip=" + skip +
                ", limit=" + limit +
                '}';
    }
}