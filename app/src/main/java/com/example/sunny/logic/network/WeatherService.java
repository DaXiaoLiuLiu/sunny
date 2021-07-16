package com.example.sunny.logic.network;

import android.util.Log;

import com.example.sunny.logic.model.DailyResponse;
import com.example.sunny.logic.model.RealtimeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherService {
    @GET("v2.5/?/{lng},{lat}/realtime.json")
    Call<RealtimeResponse> getRealtimeWeather
            (@Path("lng") String lng,
             @Path("lat") String lat);

    //此处有点小问题，应在这个两个GEI注释的？的位置填入申请的令牌值
    @GET("v2.5/?/{lng},{lat}/daily.json")
    Call<DailyResponse> getDailyWeather
            (@Path("lng") String lng,
             @Path("lat") String lat);

}
