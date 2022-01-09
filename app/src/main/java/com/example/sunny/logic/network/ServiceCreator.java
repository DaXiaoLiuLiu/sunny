package com.example.sunny.logic.network;

import com.example.sunny.BuildConfig;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceCreator<T> {
    private static String BASE_URL = "https://api.caiyunapp.com/";

    static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(initHttpClient())
            .build();

    public static PlaceService PCreate(Class<PlaceService> placeServiceClass){
       PlaceService placeService =  retrofit.create(placeServiceClass);
       return placeService;
    }

    public static WeatherService WCreate(Class<WeatherService> weatherServiceClass){
        WeatherService weatherService = retrofit.create(weatherServiceClass);
       return weatherService;
    }

    public T Create(Class<T> ServiceClass){
        T Service = retrofit.create(ServiceClass);
        return Service;
    }

    private static OkHttpClient initHttpClient() {
        Interceptor logInterceptor;
        //处理网络请求的日志拦截输出
        if (BuildConfig.DEBUG) {
            //只显示基础信息
            logInterceptor = new HttpLoggingInterceptor(new HttpLog()).
                    setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logInterceptor =
                    new HttpLoggingInterceptor(new HttpLog()).setLevel(HttpLoggingInterceptor.Level.BASIC);
        }

        return new OkHttpClient().newBuilder()
                .addInterceptor(logInterceptor)
                .build();

    }

}
