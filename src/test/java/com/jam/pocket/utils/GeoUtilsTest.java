package com.jam.pocket.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jam on 2017/1/27.
 */
public class GeoUtilsTest {

    @Test
    public void distance() throws Exception {
        Double lat1 = 23.0;
        Double lng1 = 125.0;
        Double lat2 = 23.1;
        Double lng2 = 125.1;
        Double distance = GeoUtils.distance(lat1, lng1, lat2, lng2);
        Double expect = 15110.652279745796;
        assertEquals(expect, distance);

        lat1 = 35.76361024214778;
        lng1 = 140.3841636550336;
        lat2 = 35.77365927771081;
        lng2 = 140.3884211697645;
        distance = GeoUtils.distance(lat1, lng1, lat2, lng2);
        expect = 1181.5816971704917;
        assertEquals(expect, distance);
    }

}