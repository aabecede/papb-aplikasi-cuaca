package com.example.aplikasicuaca;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {


//    List<WeatherModel> dataWeather;
    String  url = "https://api.open-meteo.com/v1/forecast?latitude=-7.98&longitude=112.63&daily=weathercode&current_weather=true&timezone=auto";
    private TextView textTemperature,
            textLatitude,
            textLongitude,
            textTimeZone,
            textWindSpeed,
            textWindDirection,
            textWeatherCode,
            textIsDay,textTime;

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
                            textTemperature.setText(jsonCurrentWinter.getString("temperature"));
                            textWindSpeed.setText(jsonCurrentWinter.getString("windspeed"));
                            textWindDirection.setText(jsonCurrentWinter.getString("winddirection"));
                            textWeatherCode.setText(jsonCurrentWinter.getString("weathercode"));
                            textIsDay.setText(jsonCurrentWinter.getString("is_day"));
                            textTime.setText(jsonCurrentWinter.getString("time"));

                            Log.e("api", "successBro: " + json);
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