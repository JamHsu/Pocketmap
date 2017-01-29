package com.jam.pocket.parser;

import com.jam.pocket.bean.tabelog.TabelogRestaurant;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by Jam on 2016/12/30.
 */
public class TabelogDataParserTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void parseCsvFile() throws Exception {
        TabelogDataParser parser = new TabelogDataParser();
        List<TabelogRestaurant> restaurantList = parser.parseCsvFile("src/test/resource/tabelog_data.csv");
        for(TabelogRestaurant restaurant : restaurantList) {
            System.out.print(restaurant.getName());
        }
    }

}