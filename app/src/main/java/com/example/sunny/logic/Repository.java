package com.example.sunny.logic;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sunny.logic.model.Place;
import com.example.sunny.logic.model.PlaceResponse;
import com.example.sunny.logic.network.SunnyWeatherNetwork;

import java.util.List;

public class Repository {
    private static List<Place> places;
    //private LiveData<List<Place>>  livePlaces ;

    public static MutableLiveData<List<Place>> searchPlaces(String query){
        MutableLiveData<List<Place>> placesData = new MutableLiveData<>();

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
}
