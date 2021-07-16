package com.example.sunny.logic.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceCreator<T> {
    private static String BASE_URL = "https://api.caiyunapp.com/";

    static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
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

    public T Create(Class<T> ServiceClass){
        T Service = retrofit.create(ServiceClass);
        return Service;
    }

}
