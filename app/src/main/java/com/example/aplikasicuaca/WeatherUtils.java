package com.example.aplikasicuaca;


    public class WeatherUtils {
        public static int getWeatherIcon(int weatherCode) {
            switch (weatherCode) {
                case 0:
                    return R.drawable.ic_clear;
                case 1:
                case 2:
                case 3:
                    return R.drawable.ic_partly_cloudy;
                case 45:
                case 48:
                    return R.drawable.ic_fog;
                case 51:
                case 53:
                case 55:
                    return R.drawable.ic_drizzle;
                case 56:
                case 57:
                    return R.drawable.ic_freezing_drizzle;
                case 61:
                case 63:
                case 65:
                    return R.drawable.ic_rain;
                case 66:
                case 67:
                    return R.drawable.ic_freezing_rain;
                case 71:
                case 73:
                case 75:
                    return R.drawable.ic_snow;
                case 77:
                    return R.drawable.ic_snow_grains;
                case 80:
                case 81:
                case 82:
                    return R.drawable.ic_rain_showers;
                case 85:
                case 86:
                    return R.drawable.ic_snow_showers;
                case 95:
                case 96:
                case 99:
                    return R.drawable.ic_thunderstorm;
                default:
                    return R.drawable.ic_unknown;
            }
        }

        public static String getWeatherText(int weatherCode) {
            switch (weatherCode) {
                case 0:
                    return "Clear";
                case 1:
                case 2:
                case 3:
                    return "Partly Cloudy";
                case 45:
                case 48:
                    return "Fog";
                case 51:
                case 53:
                case 55:
                    return "Drizzle";
                case 56:
                case 57:
                    return "Freezing Drizzle";
                case 61:
                case 63:
                case 65:
                    return "Rain";
                case 66:
                case 67:
                    return "Freezing Rain";
                case 71:
                case 73:
                case 75:
                    return "Snow";
                case 77:
                    return "Snow Grains";
                case 80:
                case 81:
                case 82:
                    return "Rain Showers";
                case 85:
                case 86:
                    return "Snow Showers";
                case 95:
                case 96:
                case 99:
                    return "Thunderstorm";
                default:
                    return "Unknown";
            }
        }
    }
