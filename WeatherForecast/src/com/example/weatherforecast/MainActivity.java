package com.example.weatherforecast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;

public class MainActivity extends Activity {

    static final String API_KEY = "e69db6b65128baece286197280e5ec71";
    static final String LATITUDE = "13.7500";
    static final String LONGITUDE = "100.4833";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        String url = String.format("https://api.forecast.io/forecast/%s/%s,%s?units=si", 
                                   API_KEY, LATITUDE, LONGITUDE);
        GetWeatherTask task = new GetWeatherTask();
        task.execute(url);
    }

    private String getWeatherData(String strUrl) {
        String strResult = "";
        try {
            URL url = new URL(strUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            strResult = readStream(con.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strResult;
    }
    
    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
    
    private class GetWeatherTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return getWeatherData(urls[0]);
        }

        @Override
        protected void onPostExecute(String jsonString) {
            String msg = "";
            
            try {
                JSONObject json = new JSONObject(jsonString);
                
                msg += String.format("Latitude: %s, Longitude: %s\n", json.getString("latitude"), json.getString("longitude"));
                msg += String.format("Timezone: %s\n\n", json.getString("timezone"));
                
                JSONObject currentWeather = json.getJSONObject("currently");
                msg += "***** Current Weather *****\n";
                msg += String.format("Temperature: %.1f\n", currentWeather.getDouble("temperature"));
                msg += String.format("Icon: %s\n", currentWeather.getString("icon"));
                msg += String.format("Summary: %s\n\n", currentWeather.getString("summary"));
                
                JSONArray dailyWeather = json.getJSONObject("daily").getJSONArray("data");
                msg += "***** Next 7 Days *****\n";
                for (int i = 0; i < dailyWeather.length(); i++) {
                    JSONObject item = dailyWeather.getJSONObject(i);
                    
                    Long timestamp = item.getLong("time") * 1000;
                    Date date = new Date(timestamp);
                    String dateString = DateFormat.format("yyyy-MM-dd hh:mm:ss", date).toString();
                    
                    msg += String.format("Date: %s\n", dateString);
                    msg += String.format("Minimum Temperature: %.1f\n", item.getDouble("temperatureMin"));
                    msg += String.format("Maximum Temperature: %.1f\n", item.getDouble("temperatureMax"));
                    msg += String.format("Icon: %s\n", item.getString("icon"));
                    msg += String.format("Summary: %s\n\n", item.getString("summary"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            
            TextView textview = (TextView) findViewById(R.id.text);
            textview.setText(msg);
        }
    }
}
