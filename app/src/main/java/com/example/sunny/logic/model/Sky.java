package com.example.sunny.logic.model;

import android.util.Log;

import com.example.sunny.R;

public class Sky {
    private String info;
    private int icon;
    private int bg;

    public Sky(String info,int icon,int bg){
        this.info = info;
        this.icon = icon;
        this.bg = bg;
    }

    private static final Sky CLEAR_DAY = new Sky("晴", R.drawable.ic_clear_day,R.drawable.bg_clear_day);
    private static final Sky CLEAR_NIGHT = new  Sky("晴", R.drawable.ic_clear_night, R.drawable.bg_clear_night);
    private static final Sky PARTLY_CLOUDY_DAY = new  Sky("多云", R.drawable.ic_partly_cloud_day, R.drawable.bg_partly_cloudy_day);
    private static final Sky PARTLY_CLOUDY_NIGHT = new  Sky("多云", R.drawable.ic_partly_cloud_night, R.drawable.bg_partly_cloudy_night);
    private static final Sky CLOUDY = new  Sky("阴", R.drawable.ic_cloudy, R.drawable.bg_cloudy);
    private static final Sky WIND = new  Sky("大风", R.drawable.ic_cloudy, R.drawable.bg_wind);
    private static final Sky LIGHT_RAIN = new  Sky("小雨", R.drawable.ic_light_rain, R.drawable.bg_rain);
    private static final Sky MODERATE_RAIN = new  Sky("中雨", R.drawable.ic_moderate_rain, R.drawable.bg_rain);
    private static final Sky HEAVY_RAIN = new  Sky("大雨", R.drawable.ic_heavy_rain, R.drawable.bg_rain);
    private static final Sky STORM_RAIN = new  Sky("暴雨", R.drawable.ic_storm_rain, R.drawable.bg_rain);
    private static final Sky THUNDER_SHOWER = new Sky("雷阵雨", R.drawable.ic_thunder_shower, R.drawable.bg_rain);
    private static final Sky SLEET = new  Sky("雨夹雪", R.drawable.ic_sleet, R.drawable.bg_rain);
    private static final Sky LIGHT_SNOW = new  Sky("小雪", R.drawable.ic_light_snow, R.drawable.bg_snow);
    private static final Sky MODERATE_SNOW = new  Sky("中雪", R.drawable.ic_moderate_snow, R.drawable.bg_snow);
    private static final Sky HEAVY_SNOW = new  Sky("大雪", R.drawable.ic_heavy_snow, R.drawable.bg_snow);
    private static final Sky STORM_SNOW = new Sky("暴雪", R.drawable.ic_heavy_snow, R.drawable.bg_snow);
    private static final Sky HAIL = new Sky("冰雹", R.drawable.ic_hail, R.drawable.bg_snow);
    private static final Sky LIGHT_HAZE = new  Sky("轻度雾霾", R.drawable.ic_light_haze, R.drawable.bg_fog);
    private static final Sky MODERATE_HAZE = new Sky("中度雾霾", R.drawable.ic_moderate_haze, R.drawable.bg_fog);
    private static final Sky HEAVY_HAZE = new  Sky("重度雾霾", R.drawable.ic_heavy_haze, R.drawable.bg_fog);
    private static final Sky FOG = new  Sky("雾", R.drawable.ic_fog, R.drawable.bg_fog);
    private static final Sky DUST = new Sky("浮尘", R.drawable.ic_fog, R.drawable.bg_fog);

    public static Sky getSky(String skycon){
        switch (skycon){
            case "CLEAR_DAY":
                return CLEAR_DAY;

            case "CLEAR_NIGHT":
                return CLEAR_NIGHT;

            case  "PARTLY_CLOUDY_DAY":
                return PARTLY_CLOUDY_DAY;

            case "PARTLY_CLOUDY_NIGHT":
                return PARTLY_CLOUDY_NIGHT;

            case "CLOUDY":
                return CLOUDY;

            case "WIND":
                return WIND;

            case "LIGHT_RAIN":
                return LIGHT_RAIN;

            case "MODERATE_RAIN":
                return MODERATE_RAIN;

            case "HEAVY_RAIN":
                return HEAVY_RAIN;

            case "STORM_RAIN":
                return STORM_RAIN;

            case "THUNDER_SHOWER":
                return THUNDER_SHOWER;

            case "SLEET":
                return SLEET;

            case "LIGHT_SNOW":
                return LIGHT_SNOW;

            case "MODERATE_SNOW":
                return MODERATE_SNOW;

            case "HEAVY_SNOW":
                return HEAVY_SNOW;

            case "STORM_SNOW":
                return STORM_SNOW;

            case "HAIL":
                return HAIL;

            case "LIGHT_HAZE":
                return LIGHT_HAZE;

            case "MODERATE_HAZE":
                return MODERATE_HAZE;

            case "HEAVY_HAZE":
                return HEAVY_HAZE;

            case "FOG":
                return FOG;

            case "DUST":
                return DUST;

            default:
                Log.d("Sky","Sky information wrong!");
        }
        return null;//这里是默认返回值，可能需要修改
    }

    public String getInfo() {
        return info;
    }

    public int getIcon() {
        return icon;
    }

    public int getBg() {
        return bg;
    }
}
