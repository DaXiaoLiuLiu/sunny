package com.example.sunny;

import android.app.Application;
import android.content.Context;

public class SunnyWeatherApplication extends Application {

    //在下面这里的 ？处 填入你申请的令牌值,问号要删掉哈
    public static final String TOKEN = "？";

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }

}
