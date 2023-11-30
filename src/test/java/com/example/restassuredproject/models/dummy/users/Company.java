package com.example.restassuredproject.models.dummy.users;

public class Company {
    public Address address;
    public String department;
    public String name;
    public String title;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Company{" +
                "address=" + address +
                ", department='" + department + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
