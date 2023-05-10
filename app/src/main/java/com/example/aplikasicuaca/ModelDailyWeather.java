package com.example.aplikasicuaca;

public class ModelDailyWeather {
    String time;
    int weatherCode;

    public ModelDailyWeather(String time, int weatherCode){
        this.time = time;
        this.weatherCode = weatherCode;
    }

    public void setWeatherCode(int weatherCode) {
        this.weatherCode = weatherCode;
    }

    public int getWeatherCode() {
        return weatherCode;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}
