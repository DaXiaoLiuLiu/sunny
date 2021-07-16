package com.example.sunny.logic.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
/*
 * @Author Lxf
 * @Date 2021/7/16 10:29
 * @Description 位置信息数据模型类
 * @Since version-1.0
 */

public class PlaceResponse {
    private  String status = new String();
    private List<Place> places = new ArrayList<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public class Place {
        private String name;
        private String address;
        //@SerializedName("formatted_address")
        private Location location;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }
    }

    public  static class Location {
        private String lat;
        private String lng;

        public Location(String lng,String lat){
            this.lng = lng;
            this.lat = lat;
        }
        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }
    }


}

