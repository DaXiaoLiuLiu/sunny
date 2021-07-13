package com.example.sunny.logic.network;

import android.util.Log;

import com.example.sunny.logic.model.DailyResponse;
import com.example.sunny.logic.model.PlaceResponse;
import com.example.sunny.logic.model.RealtimeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/*这里可能出先线程不安全问题*/
public class SunnyWeatherNetwork {
      private static PlaceService placeService = ServiceCreator.PCreate(PlaceService.class);
      private static PlaceResponse Presult = null;

      public static PlaceResponse searchPlaces(String query){

          placeService.searchPlace(query).enqueue(new Callback<PlaceResponse>() {
              @Override
              public void onResponse(Call<PlaceResponse> call, Response<PlaceResponse> response) {
                  PlaceResponse  body= response.body();
                  if(body!=null){
                      Presult = body;
                  }
                  else {
                      Log.d("SunnyWeatherNetWork","response body is null");
                  }
              }

              @Override
              public void onFailure(Call<PlaceResponse> call, Throwable t) {
                  Log.d("SunnyWeatherNetWork","连接错误");
                    t.printStackTrace();
              }
          });
          return Presult;
      }

      private static WeatherService weatherService = ServiceCreator.WCreate(WeatherService.class);

      private static RealtimeResponse Rresult;
      private static DailyResponse Dresult;

      public static RealtimeResponse getRealtimeWeather(String lng,String lat){

          weatherService.getRealtimeWeather(lng,lat).enqueue(new Callback<RealtimeResponse>() {
              @Override
              public void onResponse(Call<RealtimeResponse> call, Response<RealtimeResponse> response) {
                  if(response.body() != null){
                      Rresult = response.body();
                  }
                  else {
                      Log.d("SunnyWeatherNetWork","Realtime response body is null");
                  }
              }

              @Override
              public void onFailure(Call<RealtimeResponse> call, Throwable t) {
                  Log.d("SunnyWeatherNetWork"," RealTime connect failed");
                  t.printStackTrace();
              }
          });
          return Rresult;
      }

      public static DailyResponse getDailyWeather(String lng,String lat){
          weatherService.getDailyWeather(lng,lat).enqueue(new Callback<DailyResponse>() {
              @Override
              public void onResponse(Call<DailyResponse> call, Response<DailyResponse> response) {
                  if(response.body() != null){
                      Dresult = response.body();
                  }
                  else {
                      Log.d("SunnyWeatherNetWork","Daily response body is null");
                  }
              }

              @Override
              public void onFailure(Call<DailyResponse> call, Throwable t) {
                  Log.d("SunnyWeatherNetWork","Daily connect failed");
                  t.printStackTrace();
              }
          });
            return Dresult;
      }

}
