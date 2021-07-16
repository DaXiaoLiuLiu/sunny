package com.example.sunny.logic.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/*
 * @Author Lxf
 * @Date 2021/7/16 10:28
 * @Description 未来天气数据模型类
 * @Since version-1.0
 */

public class DailyResponse {
    private String status ;

    private Result result ;

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
        String value = new String();
        Date date = new Date();

        public String getValue() {
            return value;
        }

        public Date getDate() {
            return date;
        }
    }


    public class Daily{
        private List<Temperature> temperature = new ArrayList<>();
        private List<Skycon> skycon = new ArrayList<>();
        @SerializedName("life_index")
        private LifeIndex lifeIndex = new LifeIndex();

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
        String desc = new String();

        public String getDesc() {
            return desc;
        }
    }


    public class LifeIndex{
        List<LifeDescription> coldRisk = new ArrayList<>();
        List<LifeDescription> carWashing = new ArrayList<>();;
        List<LifeDescription> ultraviolet = new ArrayList<>();;
        List<LifeDescription> dressing = new ArrayList<>();;

        public List<LifeDescription> getColdRisk() {
            return coldRisk;
        }

        public List<LifeDescription> getCarWashing() {
            return carWashing;
        }

        public List<LifeDescription> getUltraviolet() {
            return ultraviolet;
        }

        public List<LifeDescription> getDressing() {
            return dressing;
        }
    }


    public class Result{
        Daily daily = new Daily();

        public Daily getDaily() {
            return daily;
        }

        public void setDaily(Daily daily) {
            this.daily = daily;
        }
    }
}
