package com.jam.pocket.bean.tabelog;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Jam on 2016/12/29.
 */
public class TabelogRestaurant {

    private String tabelogId;
    private String url;
    private String img_rul;
    private String name;
    private String note;
    private Double lat;
    private Double lng;
    private Double rating;
    private Address address;

    public String getTabelogId() {
        return tabelogId;
    }

    public void setTabelogId(String tabelogId) {
        this.tabelogId = tabelogId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return this.img_rul;
    }

    public void setImgUrl(String url){
        this.img_rul = url;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
