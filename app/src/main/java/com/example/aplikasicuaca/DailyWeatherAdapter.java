package com.example.aplikasicuaca;

import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DailyWeatherAdapter extends RecyclerView.Adapter<DailyWeatherAdapter.ViewHolder> {
    private List<ModelDailyWeather> mDailyWeatherList;

    public DailyWeatherAdapter(List<ModelDailyWeather> dailyWeatherList) {
        mDailyWeatherList = dailyWeatherList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflate the layout for each item in the RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_weather_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // bind the data to the views in each item of the RecyclerView
        ModelDailyWeather dailyWeatherObj = mDailyWeatherList.get(position);
        holder.timeTextView.setText(dailyWeatherObj.time);
        holder.weatherCodeTextView.setText(WeatherUtils.getWeatherText(dailyWeatherObj.weatherCode));
        holder.ivWeatherCode.setImageResource(WeatherUtils.getWeatherIcon(dailyWeatherObj.weatherCode));
    }

    @Override
    public int getItemCount() {
        return mDailyWeatherList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView timeTextView;
        TextView weatherCodeTextView;

        ImageView ivWeatherCode;

        public ViewHolder(View itemView) {
            super(itemView);
            timeTextView = (TextView) itemView.findViewById(R.id.time_text_view);
            weatherCodeTextView = (TextView) itemView.findViewById(R.id.weather_code_text_view);
            ivWeatherCode = (ImageView) itemView.findViewById(R.id.ivWeatherCode);

            timeTextView.setTextColor(Color.BLACK);
            weatherCodeTextView.setTextColor(Color.BLACK);
        }
    }
}
