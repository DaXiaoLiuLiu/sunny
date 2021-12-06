package com.example.sunny.logic.network;
/*
 * @Author Lxf
 * @Date 2021/9/5 17:16
 * @Description
 * /*这里可能出先线程不安全问题，因为这里使用了Retrofit 的同步方法
 * 这里面定义了三个方法，用于搜索位置数据，实时天气数据和
 * 未来天气信息
 * @Since version-1.0
 */

import android.util.Log;

import com.example.sunny.logic.model.DailyResponse;
import com.example.sunny.logic.model.PlaceResponse;
import com.example.sunny.logic.model.RealtimeResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SunnyWeatherNetwork {
      private static PlaceService placeService = ServiceCreator.PCreate(PlaceService.class);
      private static PlaceResponse Presult = new PlaceResponse();

      public  PlaceResponse searchPlaces(String query){

          //获取信息
          try {
              Response<PlaceResponse> response = placeService.searchPlace(query).execute();
              PlaceResponse body = response.body();
              if(body != null){
                  Presult = body;
                  Log.d("SunnyWeatherNetWork","response body is not null");
                  Log.d("SunnyWeatherNetWork","status is " +Presult.getStatus() );
              }
              else {
                  Log.d("SunnyWeatherNetWork","response body is null");
              }
          } catch (IOException e) {
              e.printStackTrace();
              Log.d("SunnyWeatherNetWork","连接错误");
          } finally {
              return Presult;
          }
/*          placeService.searchPlace(query).enqueue(new Callback<PlaceResponse>() {
              @Override
              public void onResponse(Call<PlaceResponse> call, Response<PlaceResponse> response) {
                  PlaceResponse  body= response.body();
                  if(body!=null){
                      Presult = body;
                      Log.d("SunnyWeatherNetWork","response body is not null");
                      Log.d("SunnyWeatherNetWork","status is " +Presult.getStatus() );
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
          });*/

      }

      //下面是搜索天气模块
      private final WeatherService weatherService = ServiceCreator.WCreate(WeatherService.class);

      private static RealtimeResponse Rresult = new RealtimeResponse() ;
      private static DailyResponse Dresult = new DailyResponse();

      public  RealtimeResponse getRealtimeWeather(String lng,String lat){


          try {
              Response<RealtimeResponse> response = weatherService.getRealtimeWeather(lng,lat).execute();
              if(response.body() != null){
                  Rresult = response.body();
                  Log.d("SunnyWeatherNetWork1","status is " +Rresult.getStatus() );
                  Log.d("SunnyWeatherNetWork1","Realtime response body is not null");
              }
              else {
                  Log.d("SunnyWeatherNetWork1","Realtime response body is null");
              }
          } catch (IOException e) {
              e.printStackTrace();
          }
          finally {
              return Rresult;
          }

/*          weatherService.getRealtimeWeather(lng,lat).enqueue(new Callback<RealtimeResponse>() {
              @Override
              public void onResponse(Call<RealtimeResponse> call, Response<RealtimeResponse> response) {
                  if(response.body() != null){
                      Rresult = response.body();
                      RFlag = true;
                      Log.d("SunnyWeatherNetWork1","status is " +Rresult.getStatus() );
                      Log.d("SunnyWeatherNetWork1","Realtime response body is not null");
                  }
                  else {
                      Log.d("SunnyWeatherNetWork1","Realtime response body is null");
                  }
              }

              @Override
              public void onFailure(Call<RealtimeResponse> call, Throwable t) {
                  Log.d("SunnyWeatherNetWork1"," RealTime connect failed");
                  t.printStackTrace();
              }
          });
          if(RFlag) return Rresult;
          return null;*/
      }

      //获取未来天气数据
      public DailyResponse getDailyWeather(String lng,String lat){
          Log.d("SunnyWeatherNetWork","查看传入的lng和lat" + lng + " " + lat);

          try {
              Response<DailyResponse> response = weatherService.getDailyWeather(lng,lat).execute();
              if(response.body() != null){
                  Dresult = response.body();
                  Log.d("SunnyWeatherNetWork2","Dailytime response body is not null");
                  Log.d("SunnyWeatherNetWork2","Dresult is " +Dresult.getResult() );
              }
              else {
                  Log.d("SunnyWeatherNetWork2","Daily response body is null");
              }
          } catch (IOException e) {
              e.printStackTrace();
          }
          finally {
              return Dresult;
          }
/*          weatherService.getDailyWeather(lng,lat).enqueue(new Callback<DailyResponse>() {
              @Override
              public void onResponse(Call<DailyResponse> call, Response<DailyResponse> response) {
                  if(response.body() != null){
                      Dresult = response.body();
                      DFlag = true;
                      Log.d("SunnyWeatherNetWork2","Dailytime response body is not null");
                      Log.d("SunnyWeatherNetWork2","Dresult is " +Dresult.getResult() );

                  }
                  else {
                      Log.d("SunnyWeatherNetWork2","Daily response body is null");
                  }
              }

              @Override
              public void onFailure(Call<DailyResponse> call, Throwable t) {
                  Log.d("SunnyWeatherNetWork2","Daily connect failed");
                  t.printStackTrace();
              }
          });
          if(DFlag) return Dresult;
            return null;*/
      }

}
