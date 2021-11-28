package com.ripalay.weather.di;

import android.content.Context;

import com.ripalay.weather.data.local.AppDatabase;
import com.ripalay.weather.data.local.RoomClient;
import com.ripalay.weather.data.local.WeatherDao;
import com.ripalay.weather.data.remote.RetrofitClient;
import com.ripalay.weather.data.remote.WeatherApi;
import com.ripalay.weather.data.repositories.MainRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {

    @Provides
    public static WeatherApi provideApi() {
        return new RetrofitClient().provideApi();
    }

    @Provides
    public static MainRepository provideMainRepository(WeatherApi api, WeatherDao dao) {
        return new MainRepository(api, dao);
    }

    @Provides
    public static AppDatabase provideAppDatabase(@ApplicationContext Context context) {
        return new RoomClient().provideDatabase(context);
    }
    @Provides
    public static WeatherDao provideWeatherDao(AppDatabase database){
        return database.weatherDao();
    }

}
