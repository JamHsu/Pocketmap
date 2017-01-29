package com.jam.pocket.controller;

import com.jam.pocket.bean.geo.Point;
import com.jam.pocket.bean.tabelog.TabelogRestaurant;
import com.jam.pocket.service.TabelogService;
import com.jam.pocket.utils.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Jam on 2017/1/8.
 */
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private static final Logger logger = Logger.getLogger(RestaurantController.class);

    @Autowired
    private TabelogService service;

    @RequestMapping(value ="/tabelog", method = RequestMethod.GET)
    public ResponseEntity<Object> listTabelogRestaurant(HttpServletRequest request) throws Exception {
        List<TabelogRestaurant> restaurantList = service.listData();
        logger.debug("List restaurant count: " + restaurantList.size());
        return new ResponseEntity<Object>(restaurantList, HttpStatus.OK);
    }

    @RequestMapping(value ="/tabelog/nearest", method = RequestMethod.GET)
    public ResponseEntity<Object> listNearestTabelogRestaurant(HttpServletRequest request,
                                                               @RequestParam(value="lat") Double lat,
                                                               @RequestParam(value="lng") Double lng)
            throws Exception {
        Point point = new Point(lat, lng);
        Integer DISTANCE = 1000; // unit: meter
        List<TabelogRestaurant> restaurantList = service.listNearestData(point, DISTANCE);
        logger.debug("List near restaurant count: " + restaurantList.size());
        return new ResponseEntity<Object>(restaurantList, HttpStatus.OK);
    }

//    curl -v -F name=test.csv -F file=@tabelog_data.csv http://127.0.0.1:8080/restaurant/tabelog/upload
    @RequestMapping(value ="/tabelog/upload", method = RequestMethod.POST)
    public void uploadTabelogRestaurant(@RequestParam("name") String name,
                                        @RequestParam("file") MultipartFile file) throws Exception {
        try {
            File tempCsvFile = FileUtils.saveTempFile(file);
            service.processCsvFile(tempCsvFile);
        } catch (Exception e) {
            logger.error("Upload file failed.", e);
            throw e;
        }
    }



}
