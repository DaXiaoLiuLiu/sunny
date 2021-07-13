package com.example.sunny.ui.place;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.sunny.logic.Repository;
import com.example.sunny.logic.dao.PlaceDao;
import com.example.sunny.logic.model.PlaceResponse;

import java.util.ArrayList;
import java.util.List;

public class PlaceViewModel extends ViewModel {
    private  static MutableLiveData<String> searchLiveData =
            new MutableLiveData<>();

    private static List<PlaceResponse.Place> placeList = new ArrayList<>();

    LiveData<List<PlaceResponse.Place>> placeLiveData = Transformations.switchMap(searchLiveData,
            query -> Repository.searchPlaces(query));

    public void searchPlace(String query){
        searchLiveData.postValue(query);
    }



    public List<PlaceResponse.Place> getPlaceList() {
        return placeList;
    }

    public void setPlaceList(List<PlaceResponse.Place> placeList) {
        this.placeList = placeList;
    }

    public void listClear(){
        placeList.clear();
    }

    public void addAllList(List<PlaceResponse.Place> places){
        placeList.addAll(places);
    }


    //用于物理存储位置信息
    public void savePlace(PlaceResponse.Place place){
        Repository.savePlace(place);
    }

    public  PlaceResponse.Place getSavedPlace(){
        return Repository.getSavedPlace();
    }

    public  Boolean isPlaceSaved(){
        return Repository.isPlaceSaved();
    }
}
