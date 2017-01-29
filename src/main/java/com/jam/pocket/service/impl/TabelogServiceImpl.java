package com.jam.pocket.service.impl;

import com.jam.pocket.bean.geo.Point;
import com.jam.pocket.bean.tabelog.TabelogRestaurant;
import com.jam.pocket.dao.TabelogDao;
import com.jam.pocket.exception.ConvertFailedException;
import com.jam.pocket.parser.TabelogDataParser;
import com.jam.pocket.service.TabelogService;
import com.jam.pocket.service.abs.BaseService;
import com.jam.pocket.utils.CmdUtils;
import com.jam.pocket.utils.GeoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jam on 2017/1/8.
 */
@Service
public class TabelogServiceImpl extends BaseService implements TabelogService {

    @Autowired
    private TabelogDao dao;

    @Override
    public int create(TabelogRestaurant tabelogRestaurant) throws Exception {
        return dao.createRestaurant(tabelogRestaurant);
    }

    @Override
    public List<TabelogRestaurant> listData() throws Exception {
        return dao.getAllRestaurant();
    }

    @Override
    public List<TabelogRestaurant> listDataByRegion(String region) {
        return null;
    }

    @Override
    public List<TabelogRestaurant> listDataByLocality(String locality) {
        return null;
    }

    @Override
    public List<TabelogRestaurant> listNearestData(Point point, Integer distance) {
        List<TabelogRestaurant> tabelogRestaurantList = dao.getAllRestaurant();
        List<TabelogRestaurant> nearestList = new ArrayList<TabelogRestaurant>();
        logger.debug("Location: " + point);
        for(TabelogRestaurant restaurant : tabelogRestaurantList) {
            Double distanceWithRestaurant = GeoUtils.distance(
                                                point.getLatitude(), point.getLongitude(),
                                                restaurant.getLat(), restaurant.getLng());
            if(distanceWithRestaurant.intValue() <= distance.intValue()) {
                nearestList.add(restaurant);
            }

        }

        // TODO sort
        // TODO find nearest 1 km restaruant
        return nearestList;
    }

    @Override
    public TabelogRestaurant getData(Integer id) throws Exception {
        return dao.getRestaurant(id);
    }

    @Override
    public void update(TabelogRestaurant tabelogRestaurant) throws Exception {
    }

    @Override
    public void delete(Integer id) throws Exception {
        dao.delRestaurant(id);
    }

    @Override
    public void processCsvFile(File csvFile) {
        try {
            String targetFile = csvFile.getParent() + File.separator + "convert_result.csv";
            logger.debug("Covert to useful data at: " +  targetFile);
            Boolean convertSuccess = CmdUtils.runPythonConvert(csvFile.getAbsolutePath(), targetFile);
            if(!convertSuccess) {
                throw new ConvertFailedException();
            }
            logger.debug("Parse restaurant data from " + targetFile);
            List<TabelogRestaurant> restaurantList = parseRestaurantDataFromCsv(targetFile);
            saveData(restaurantList);
        } finally {

        }
    }

    private void saveData(List<TabelogRestaurant> restaurantList) {
        dao.batchCreateRestaurant(restaurantList);
    }

    private List<TabelogRestaurant> parseRestaurantDataFromCsv(String csvFilePath) {
        TabelogDataParser parser = new TabelogDataParser();
        List<TabelogRestaurant> restaurantList = new ArrayList<TabelogRestaurant>();
        try {
            restaurantList = parser.parseCsvFile(csvFilePath);
            logger.debug("Parse restaurant count: " + restaurantList.size());
        } catch (IOException e) {
            logger.warn("Parse data from csv failed: " + e.getMessage());
        }

        return restaurantList;
    }

}
