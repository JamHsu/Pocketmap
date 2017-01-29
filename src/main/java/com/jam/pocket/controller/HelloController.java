package com.jam.pocket.controller;

import com.jam.pocket.bean.tabelog.TabelogRestaurant;
import com.jam.pocket.dao.TabelogDao;
import com.jam.pocket.parser.TabelogDataParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jam on 2017/1/2.
 */
@RestController
@RequestMapping("/test")
public class HelloController {

    @Autowired
    private TabelogDao tabelogDao;

    @RequestMapping(value ="/hello", method = RequestMethod.GET)
    public ResponseEntity<Object> hello(HttpServletRequest request) {
        return new ResponseEntity<Object>("hello", HttpStatus.OK);
    }

    @RequestMapping(value ="/testWrite", method = RequestMethod.GET)
    public ResponseEntity<Object> testWrite(HttpServletRequest request) throws Exception {
        TabelogDataParser parser = new TabelogDataParser();
        List<TabelogRestaurant> restaurantList = parser.parseCsvFile("src/test/resource/tabelog_data.csv");
//        for(TabelogRestaurant restaurant : restaurantList) {
//            tabelogDao.createRestaurant(restaurant);
//        }
        return new ResponseEntity<Object>(restaurantList, HttpStatus.OK);
    }

}
