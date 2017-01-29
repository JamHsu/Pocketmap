package com.jam.pocket.service;

import com.jam.pocket.bean.geo.Point;
import com.jam.pocket.bean.tabelog.TabelogRestaurant;
import com.jam.pocket.service.interfaces.CrudService;

import java.io.File;
import java.util.List;

/**
 * Created by Jam on 2017/1/8.
 */
public interface TabelogService extends CrudService<TabelogRestaurant> {

    public List<TabelogRestaurant> listDataByRegion(String region);

    public List<TabelogRestaurant> listDataByLocality(String locality);

    public List<TabelogRestaurant> listNearestData(Point point, Integer distance);

    public void processCsvFile(File csvFilePath);
}
