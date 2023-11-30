package com.example.restassuredproject.models.dummy.users;

public class Coordinates{
    public double lat;
    public double lng;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
