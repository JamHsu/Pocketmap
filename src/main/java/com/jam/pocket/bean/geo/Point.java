package com.jam.pocket.bean.geo;

/**
 * Created by Jam on 2017/1/21.
 */
public class Point {

    private Double latitude;
    private Double longitude;

    public Point(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return String.format("[lat]=%s, [lng]=%s", this.latitude.toString(), this.longitude.toString());
    }

}
