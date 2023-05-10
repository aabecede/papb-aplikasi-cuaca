package com.example.aplikasicuaca;

public class WeatherModel {
    float latitude;

    public WeatherModel(float latitude){
        this.latitude = latitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    //    float longitude: 112.625,
//    generationtime_ms: 0.15401840209960938,
//    utc_offset_seconds: 25200,
//    timezone: "Asia/Jakarta",
//    timezone_abbreviation: "WIB",
//    elevation: 446,
//    current_weather: {
//        temperature: 26.8,
//                windspeed: 2.4,
//                winddirection: 63,
//                weathercode: 3,
//                is_day: 1,
//                time: "2023-05-10T16:00"
//    }
}
