package com.jam.pocket.dao.mapper;

import com.jam.pocket.bean.tabelog.TabelogRestaurant;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Jam on 2017/1/2.
 */
@Mapper
public interface TabelogRestaurantMapper {

    @Insert({"INSERT INTO tabelog_restaurant (tabelog_id, url, img_url, name, note, lat, lng, rating) ",
            "VALUES (#{tabelogId}, #{url}, #{img_url}, #{name}, #{note}, #{lat}, #{lng}, #{rating})"})
    @Options(useGeneratedKeys = true, keyColumn = "id")
    public int createRestaurant(TabelogRestaurant restaurant);

    @Insert({
            "<script>",
            "INSERT IGNORE INTO tabelog_restaurant (tabelog_id, url, img_url, name, note, lat, lng, rating) VALUES",
            "<foreach collection='restaurantList' item='restaurant' separator=','>",
            "(#{restaurant.tabelogId}, #{restaurant.url}, #{restaurant.img_rul}, #{restaurant.name}, #{restaurant.note}, #{restaurant.lat}, #{restaurant.lng}, #{restaurant.rating})",
            "</foreach>",
            "</script>"
    })
    public void batchCreateRestaurant(@Param("restaurantList") List<TabelogRestaurant> restaurantList);

    @Select("SELECT * FROM tabelog_restaurant")
    public List<TabelogRestaurant> getAllRestaurant();

    @Select("SELECT * FROM tabelog_restaurant WHERE tabelog_id = #{id}")
    public TabelogRestaurant getRestaurant(Integer id);

    @Update("UPDATE tabelog_restaurant SET name = #{name}, lat = #{lat}, lng = #{lng}, rating = #{rating}" +
            " WHERE tabelog_id = #{tabelogId}")
    public void updateRestaurant(TabelogRestaurant restaurant);

    @Delete("DELETE FROM tabelog_restaurant WHERE tabelog_id = #{id}")
    public void deleteRestaurant(Integer id);

}
