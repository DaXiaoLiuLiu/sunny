package com.example.sunny.logic.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class DailyResponse {
    private String status;

    private Result result;

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


    //下面是内部类
    public class Temperature{
        Float max;
        Float min;

        public Float getMax() {
            return max;
        }

        public Float getMin() {
            return min;
        }
    }


    public class Skycon{
        String value;
        Date date;

        public String getValue() {
            return value;
        }

        public Date getDate() {
            return date;
        }
    }


    public class Daily{
        private List<Temperature> temperature;
        private List<Skycon> skycon;
        @SerializedName("life_index")
        private LifeIndex lifeIndex;

        public List<Temperature> getTemperature() {
            return temperature;
        }

        public List<Skycon> getSkycon() {
            return skycon;
        }

        public LifeIndex getLifeIndex() {
            return lifeIndex;

        }
    }


    public class LifeDescription{
        String desc;

        public String getDesc() {
            return desc;
        }
    }


    public class LifeIndex{
        List<LifeDescription> coldRisk;
        List<LifeDescription> caeWashing;
        List<LifeDescription> ultraviolet;
        List<LifeDescription> dressing;

        public List<LifeDescription> getColdRisk() {
            return coldRisk;
        }

        public List<LifeDescription> getCaeWashing() {
            return caeWashing;
        }

        public List<LifeDescription> getUltraviolet() {
            return ultraviolet;
        }

        public List<LifeDescription> getDressing() {
            return dressing;
        }
    }


    public class Result{
        Daily daily;

        public Daily getDaily() {
            return daily;
        }

        public void setDaily(Daily daily) {
            this.daily = daily;
        }
    }
}
