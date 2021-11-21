package com.ripalay.weather;

import android.app.Application;

import com.ripalay.weather.data.remote.RetrofitClient;
import com.ripalay.weather.data.remote.WeatherApi;

public class App extends Application {
    public static WeatherApi api;
    private RetrofitClient retrofitClient;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofitClient = new RetrofitClient();
        api = retrofitClient.provideApi();
    }
}
