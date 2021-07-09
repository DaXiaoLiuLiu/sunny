package com.example.sunny.ui.place;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.sunny.logic.Repository;
import com.example.sunny.logic.model.Place;

import java.util.ArrayList;
import java.util.List;

public class PlaceViewModel extends ViewModel {
    private  static MutableLiveData<String> searchLiveData =
            new MutableLiveData<>();

    private static List<Place> placeList = new ArrayList<>();

    LiveData<List<Place>> placeLiveData = Transformations.switchMap(searchLiveData,
            query -> Repository.searchPlaces(query));

    public void searchPlace(String query){
        searchLiveData.postValue(query);
    }



    public List<Place> getPlaceList() {
        return placeList;
    }

    public void setPlaceList(List<Place> placeList) {
        this.placeList = placeList;
    }

    public void listClear(){
        placeList.clear();
    }

    public void addAllList(List<Place> places){
        placeList.addAll(places);
    }
}
