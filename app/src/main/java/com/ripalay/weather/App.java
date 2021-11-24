package com.ripalay.weather;

import android.app.Application;

import com.ripalay.weather.data.remote.RetrofitClient;
import com.ripalay.weather.data.remote.WeatherApi;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
