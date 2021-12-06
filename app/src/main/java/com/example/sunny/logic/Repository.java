package com.example.sunny.logic;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.sunny.logic.dao.PlaceDao;
import com.example.sunny.logic.model.DailyResponse;

import com.example.sunny.logic.model.PlaceResponse;
import com.example.sunny.logic.model.RealtimeResponse;
import com.example.sunny.logic.model.Weather;
import com.example.sunny.logic.network.SunnyWeatherNetwork;

import java.util.List;

import static java.lang.Thread.sleep;
/*
 * @Author Lxf
 * @Date 2021/7/16 10:29
 * @Description 仓库类，封装了许多数据获取操作，提供数据给ViewModel层，同时接收ViewModel层的数据请求
 * @Since version-1.0
 */

public class Repository {
    private static List<PlaceResponse.Place> places;
    //private LiveData<List<Place>>  livePlaces ;
    private static MutableLiveData<List<PlaceResponse.Place>> placesData =
            new MutableLiveData<>();
    final static SunnyWeatherNetwork sunnyWeatherNetwork = new SunnyWeatherNetwork();

    //由于是静态方法，创建时就会调用一次
    public  MutableLiveData<List<PlaceResponse.Place>> searchPlaces(String query){


        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    /*获取到搜索到的数据，在network层已经进行判空处理，保证了接收到的placeResponse不为null
                    * 但由于网络请求的同步问题，每次发出的第一个请求的结果，会导致placeResponse为 null，
                    * 因此在PlaceResponse中，需要new 一个 status和result，避免空指针异常。
                    *
                    * */
                    final PlaceResponse placeResponse = sunnyWeatherNetwork.searchPlaces(query);
                    if (placeResponse.getStatus().equals("ok")) {//如果状态ok了，一般来说places就不会有空指针异常
                        places = placeResponse.getPlaces();// 获取到包含地区信息的list
                        Log.d("Repository","place response success " );
                        placesData.postValue(places);//将list传入Livedata内，并准备返回
                    } else {
                        //返回状态不是ok的情况
                        Log.d("Repository", "place status is" + placeResponse.getStatus() );
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("Repository","PlaceResponse Error!");
                } finally {
                    Log.d("Repository","PlaceResponse finish!");
                }

            }
        }).start();
        return placesData;//返回livedata
    }

    //这里属于显示天气信息模块中的刷新天气时用到的方法
    public   MutableLiveData<Weather> refreshWeather(String lng,String lat){
        MutableLiveData<Weather> weatherData = new MutableLiveData<>();

        new Thread(new Runnable() {
            @Override
            public void run() {

                try{
                    RealtimeResponse realtimeResponse ;
                    DailyResponse dailyResponse ;

                    //这里已经解决了线程同步问题，不会出现空指针异常

                    realtimeResponse = sunnyWeatherNetwork.getRealtimeWeather(lng, lat);
                    dailyResponse = sunnyWeatherNetwork.getDailyWeather(lng, lat);
                    Log.d("Repository","refresh Weather 数据申请中");

                    if(realtimeResponse.getStatus().equals("ok") && dailyResponse.getStatus().equals("ok")){
                        Weather weather = new Weather(realtimeResponse.getResult().getRealtime(),
                                dailyResponse.getResult().getDaily() );

                        weatherData.postValue(weather);

                    }
                    else {

                        Log.d("Repository", "Daily response status is" + dailyResponse.getStatus());
                        Log.d("Repository", "Realtime response error is" + realtimeResponse.getError());

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

