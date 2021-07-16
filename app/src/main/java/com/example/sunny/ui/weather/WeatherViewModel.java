package com.example.sunny.ui.weather;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.sunny.logic.Repository;

import com.example.sunny.logic.model.PlaceResponse;
import com.example.sunny.logic.model.Weather;

import static android.os.Build.VERSION_CODES.P;

public class WeatherViewModel extends ViewModel {

    private static MutableLiveData<PlaceResponse.Location> locationLiveData =
            new MutableLiveData<>();
    public String locationLng = "";
    public String locationLat = "";
    public String placeName = "";
    final static Repository repository = new Repository();

    public static final LiveData<Weather> weatherLiveData = Transformations.switchMap(locationLiveData,
            location -> repository.refreshWeather(location.getLng(),location.getLat()));


    public void refreshWeather(String lng,String lat){
        Log.d("WeatherViewModel","lng is " + lng);
        locationLiveData.postValue(new PlaceResponse.Location(lng,lat));
    }

    public MutableLiveData<PlaceResponse.Location> getLocationLiveData() {
        return locationLiveData;
    }
}
