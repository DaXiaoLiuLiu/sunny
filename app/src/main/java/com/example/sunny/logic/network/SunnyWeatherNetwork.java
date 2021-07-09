package com.example.sunny.logic.network;

import android.util.Log;

import com.example.sunny.logic.model.PlaceResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/*这里可能出先线程不安全问题*/
public class SunnyWeatherNetwork {
      private static PlaceService placeService = ServiceCreator.ServiceCreate(PlaceService.class);
      private static PlaceResponse result = null;

      public static PlaceResponse searchPlaces(String query){

          placeService.searchPlace(query).enqueue(new Callback<PlaceResponse>() {
              @Override
              public void onResponse(Call<PlaceResponse> call, Response<PlaceResponse> response) {
                  PlaceResponse  body= response.body();
                  if(body!=null){
                      result = body;
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
          return result;
      }



}
