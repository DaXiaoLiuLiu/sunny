package com.example.sunny.ui.weather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.sunny.logic.Repository;

import com.example.sunny.logic.model.PlaceResponse;
import com.example.sunny.logic.model.Weather;

import static android.os.Build.VERSION_CODES.P;

public class WeatherViewModel extends ViewModel {

    private MutableLiveData<PlaceResponse.Location> locationLiveData = new MutableLiveData<>();
    public String locationLng = "";
    public String locationLat = "";
    public String placeName = "";

    public LiveData<Weather> weatherLiveData = Transformations.switchMap(locationLiveData,
            location -> Repository.refreshWeather(location.getLng(),location.getLat()));

    public void refreshWeather(String lng,String lat){

        locationLiveData.postValue(new PlaceResponse.Location(lng,lat));
    }

    public MutableLiveData<PlaceResponse.Location> getLocationLiveData() {
        return locationLiveData;
    }
}
