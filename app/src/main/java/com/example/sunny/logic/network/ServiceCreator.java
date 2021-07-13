package com.example.sunny.logic.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceCreator {
    private static String BASE_HRL = "https://api.caiyunapp.com/";

    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_HRL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static PlaceService PCreate(Class<PlaceService> placeServiceClass){
       PlaceService placeService =  retrofit.create(placeServiceClass);
       return placeService;
    }

    public static WeatherService WCreate(Class<WeatherService> weatherServiceClass){
        WeatherService weatherService = retrofit.create(weatherServiceClass);
        return weatherService;
    }
}
