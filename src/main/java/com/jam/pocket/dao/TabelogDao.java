package com.jam.pocket.dao;

import com.jam.pocket.dao.mapper.TabelogRestaurantMapper;
import com.jam.pocket.bean.tabelog.TabelogRestaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jam on 2017/1/2.
 */
@Repository
public class TabelogDao {

    @Autowired
    private TabelogRestaurantMapper restaurantMapper;

    public int createRestaurant(TabelogRestaurant restaurant) {
        return restaurantMapper.createRestaurant(restaurant);
    }

    public void batchCreateRestaurant(List<TabelogRestaurant> restaurantList) {
        restaurantMapper.batchCreateRestaurant(restaurantList);
    }

    public List<TabelogRestaurant> getAllRestaurant() {
        return restaurantMapper.getAllRestaurant();
    }

    public TabelogRestaurant getRestaurant(Integer id) {
        return restaurantMapper.getRestaurant(id);
    }

    public void delRestaurant(Integer id) {
        restaurantMapper.deleteRestaurant(id);
    }

}
