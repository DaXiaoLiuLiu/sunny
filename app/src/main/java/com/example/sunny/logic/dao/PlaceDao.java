package com.example.sunny.logic.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sunny.SunnyWeatherApplication;
import com.example.sunny.logic.model.PlaceResponse;
import com.google.gson.Gson;

public class PlaceDao {
    private static final SharedPreferences sharedPreferences = SunnyWeatherApplication.getContext()
            .getSharedPreferences("placeData", Context.MODE_PRIVATE);

    public static void savePlace(PlaceResponse.Place place){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString("place", gson.toJson(place));
    }

    public static PlaceResponse.Place getSavedPlace(){
        Gson gson = new Gson();
         return gson.fromJson(sharedPreferences.getString("place", "")
                 , PlaceResponse.Place.class);
    }

    public static Boolean isPlaceSaved(){
        return sharedPreferences.contains("place");
    }


}

