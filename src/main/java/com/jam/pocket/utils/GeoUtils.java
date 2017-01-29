package com.jam.pocket.utils;

/**
 * Created by Jam on 2017/1/21.
 */
public class GeoUtils {

    /**
     * Calculates the distance in km between two lat/long points
     * using the haversine formula
     */
    /**
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return distance, unit is meter
     */
    public static double distance(
            double lat1, double lng1, double lat2, double lng2) {
        int r = 6371; // average radius of the earth in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                        * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = r * c; // km
        return d * 1000;
    }


}
