package com.example.sunny.logic.model;

import com.google.gson.annotations.SerializedName;

/*
 * @Author Lxf
 * @Date 2021/7/16 10:19
 * @Description 实时天气信息数据模型
 * @Since version-1.0
 */

public class RealtimeResponse {
    private String status ;
    private Result result ;
    private String error = new String();
    //创建一个error变量，可以方便进行网络请求层面的调试

    public String getError() {
        return error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    //下面是一些内部类
    public class AQI{
        private Float chn;

        public Float getChn() {
            return chn;
        }

        public void setChn(Float chn) {
            this.chn = chn;
        }
    }

    public class AirQuality{
        private AQI aqi;

        public AQI getAqi() {
            return aqi;
        }

        public void setAqi(AQI aqi) {
            this.aqi = aqi;
        }
    }

    public class Realtime{
        private String skycon;
        private Float temperature;
        @SerializedName("air_quality")
        private AirQuality airQuality;

        public String getSkycon() {
            return skycon;
        }

        public void setSkycon(String skycon) {
            this.skycon = skycon;
        }

        public Float getTemperature() {
            return temperature;
        }

        public void setTemperature(Float temperature) {
            this.temperature = temperature;
        }

        public AirQuality getAirQuality() {
            return airQuality;
        }

        public void setAirQuality(AirQuality airQuality) {
            this.airQuality = airQuality;
        }
    }

    public class Result{
        private Realtime realtime;

        public Realtime getRealtime() {
            return realtime;
        }

        public void setRealtime(Realtime realtime) {
            this.realtime = realtime;
        }
    }
}
