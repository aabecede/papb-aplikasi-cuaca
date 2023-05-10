package com.example.aplikasicuaca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    List<ModelDailyWeather> modelDailyWeathers = new ArrayList<>();
    String  url = "https://api.open-meteo.com/v1/forecast?latitude=-7.98&longitude=112.63&daily=weathercode&current_weather=true&timezone=auto";
    private TextView textTemperature,
            textLatitude,
            textLongitude,
            textTimeZone,
            textWindSpeed,
            textWindDirection,
            textWeatherCode,
            textIsDay,textTime,textWeatherCodeText;
    private RecyclerView rvDailyWeather;
    private ImageView ibWeatherImg;
    private ConstraintLayout rootLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textLatitude = (TextView) findViewById(R.id.idLatitude);
        textLongitude = (TextView) findViewById(R.id.idLongitude);
        textTimeZone = (TextView) findViewById(R.id.idTimeZone);
        textTemperature = (TextView) findViewById(R.id.idTemperature);
        textWindSpeed = (TextView) findViewById(R.id.idWindSpeed);
        textWindDirection = (TextView) findViewById(R.id.idWindDirection);
        textWeatherCode = (TextView) findViewById(R.id.idWeatherCode);
        textIsDay = (TextView) findViewById(R.id.idIsDay);
        textTime = (TextView) findViewById(R.id.idTime);
        textWeatherCodeText = (TextView) findViewById(R.id.idWeatherCodeText);

        rvDailyWeather = (RecyclerView) findViewById(R.id.rvDailyWeather);
        rvDailyWeather.setLayoutManager(new LinearLayoutManager( this));

        ibWeatherImg = (ImageView) findViewById(R.id.idImageWeather);
        getData();
    }

    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject json = new JSONObject(response);
                            textLatitude.setText(json.getString("latitude"));
                            textLongitude.setText(json.getString("longitude"));
                            textTimeZone.setText(json.getString("timezone"));

                            JSONObject jsonCurrentWinter = json.getJSONObject("current_weather");
                            String weatherCode = jsonCurrentWinter.getString("weathercode");
                            String isDay = jsonCurrentWinter.getString("is_day");
                            textTemperature.setText(jsonCurrentWinter.getString("temperature"));
                            textWindSpeed.setText(jsonCurrentWinter.getString("windspeed"));
                            textWindDirection.setText(jsonCurrentWinter.getString("winddirection"));
                            textWeatherCode.setText(weatherCode);
                            textIsDay.setText(isDay);
                            textTime.setText(jsonCurrentWinter.getString("time"));
                            ibWeatherImg.setImageResource(WeatherUtils.getWeatherIcon(Integer.parseInt(weatherCode)));
                            textWeatherCodeText.setText(WeatherUtils.getWeatherText(Integer.parseInt(weatherCode)));

                            //change backgroundColor
                            // Get a reference to the root layout of your activity (e.g., ConstraintLayout, RelativeLayout)
                            rootLayout = (ConstraintLayout) findViewById(R.id.root_layout); // Replace with the ID of your root layout
                            if(Integer.parseInt(isDay) == 1){ //if day
                                // Create a GradientDrawable for the background
                                GradientDrawable gradientDrawable = new GradientDrawable(
                                        GradientDrawable.Orientation.TOP_BOTTOM, // Set the gradient orientation from top to bottom
                                        new int[] {Color.YELLOW, Color.WHITE} // Define the colors for the gradient
                                );
                                rootLayout.setBackground(gradientDrawable);
                            }
                            else{ //night
                                // Create a GradientDrawable for the background
                                GradientDrawable gradientDrawable = new GradientDrawable(
                                        GradientDrawable.Orientation.TOP_BOTTOM, // Set the gradient orientation from top to bottom
                                        new int[] {Color.CYAN, Color.WHITE} // Define the colors for the gradient
                                );
                                rootLayout.setBackground(gradientDrawable);
                            }


                            // Set the background of the root layout as the GradientDrawable


                            JSONObject jsonDaily =  json.getJSONObject("daily");
                            JSONArray jsonDailyTime = jsonDaily.getJSONArray("time");
                            JSONArray jsonWeatherCode = jsonDaily.getJSONArray("weathercode");
                            int jumlahTime = jsonDaily.getJSONArray("time").length();

                            for (int i=0; i< jumlahTime; i++){
                                ModelDailyWeather dailyWeatherObj = new ModelDailyWeather(jsonDailyTime.getString(i), jsonWeatherCode.getInt(i));
                                modelDailyWeathers.add(dailyWeatherObj);
                            }

                            rvDailyWeather.setAdapter(new DailyWeatherAdapter(MainActivity.this.modelDailyWeathers));
                        } catch (Throwable e){
                            e.printStackTrace();
                            Log.e("api", "error: " + e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("api", "onErrorResponse: " + error.getLocalizedMessage());
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}