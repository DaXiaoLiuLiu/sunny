package com.example.sunny.ui.place;

import android.util.Log;

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
    private Repository repository = new Repository() ;

    private  static List<PlaceResponse.Place> placeList = new ArrayList<>();

    public final LiveData<List<PlaceResponse.Place>> placeLiveData = Transformations.switchMap(searchLiveData,
            query -> repository.searchPlaces(query));

    public void searchPlace(String query){
        Log.d("PlaceViewModel","query is " + query);
        searchLiveData.postValue(query);
    }


    public List<PlaceResponse.Place> getPlaceList() {
        return placeList;
    }

    public void listClear(){
        placeList.clear();
    }

    public void addAllList(List<PlaceResponse.Place> places){
        placeList.addAll(places);
    }


    //用于本地存储位置信息
    public static void savePlace(PlaceResponse.Place place){
        Repository.savePlace(place);
    }

    public  static PlaceResponse.Place getSavedPlace(){
        return Repository.getSavedPlace();
    }

    public static Boolean isPlaceSaved(){
        return Repository.isPlaceSaved();
    }
}
