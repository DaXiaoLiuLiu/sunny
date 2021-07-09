package com.example.sunny.logic.model;

import com.example.sunny.logic.model.Place;

import java.util.ArrayList;
import java.util.List;

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
}
