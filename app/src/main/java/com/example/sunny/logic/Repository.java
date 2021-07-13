package com.example.sunny.logic;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sunny.logic.dao.PlaceDao;
import com.example.sunny.logic.model.DailyResponse;

import com.example.sunny.logic.model.PlaceResponse;
import com.example.sunny.logic.model.RealtimeResponse;
import com.example.sunny.logic.model.Weather;
import com.example.sunny.logic.network.SunnyWeatherNetwork;

import java.util.List;

public class Repository {
    private static List<PlaceResponse.Place> places;
    //private LiveData<List<Place>>  livePlaces ;

    public static MutableLiveData<List<PlaceResponse.Place>> searchPlaces(String query){
        MutableLiveData<List<PlaceResponse.Place>> placesData = new MutableLiveData<>();

        new Thread(new Runnable() {
            @Override

            public void run() {


                try {
                    PlaceResponse placeResponse = SunnyWeatherNetwork.searchPlaces(query);

                    if (placeResponse.getStatus() == "ok") {
                        places = placeResponse.getPlaces();
                        placesData.postValue(places);
                    } else {
                        Log.d("Repository", "response status is" + placeResponse.getStatus());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("Repository","PlaceResponse Error!");
                } finally {
                    Log.d("Repository","PlaceResponse finsh!");
                }

            }
        }).start();
        return placesData;
    }

    public static MutableLiveData<Weather> refreshWeather(String lng,String lat){
        MutableLiveData<Weather> weatherData = new MutableLiveData<>();

        new Thread(new Runnable() {

            @Override
            public void run() {
                DailyResponse dailyResponse = new DailyResponse();
                RealtimeResponse realtimeResponse = new RealtimeResponse();


                try{
                    realtimeResponse =SunnyWeatherNetwork.getRealtimeWeather(lng,lat);
                    dailyResponse = SunnyWeatherNetwork.getDailyWeather(lng,lat);

                    if(realtimeResponse.getStatus() == "ok" && dailyResponse.getStatus() == "ok"){
                        Weather weather = new Weather(realtimeResponse.getResult().getRealtime(),
                                dailyResponse.getResult().getDaily() );
                        weatherData.postValue(weather);

                    }
                    else {
                        Log.d("Repository", "Daily response status is" + dailyResponse.getStatus());
                        Log.d("Repository", "Realtime response status is" + realtimeResponse.getStatus());

                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                    Log.d("Repository","weather network Error!");
                }
                finally {
                    Log.d("Repository","refresh finish!");
                }
            }
        }).start();
        return weatherData;
    }

    //物理存储地点信息
    public static void savePlace(PlaceResponse.Place place){
        PlaceDao.savePlace(place);
    }

    public static PlaceResponse.Place getSavedPlace(){
        return PlaceDao.getSavedPlace();
    }

    public static Boolean isPlaceSaved(){
        return PlaceDao.isPlaceSaved();
    }
}
