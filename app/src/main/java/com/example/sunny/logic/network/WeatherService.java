package com.example.sunny.logic.network;

import com.example.sunny.logic.model.DailyResponse;
import com.example.sunny.logic.model.RealtimeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherService {
    @GET("v2.5/${SunnyWeatherApplication.Token}/{lng},{lat}/realtime.json")
    Call<RealtimeResponse> getRealtimeWeather
            (@Path("lng") String lng,
             @Path("lat") String lat);

    @GET("v2.5/${SunnyWeatherApplication.Token}/{lng},{lat}/daily.json")
    Call<DailyResponse> getDailyWeather
            (@Path("lng") String lng,
             @Path("lat") String lat);

}
