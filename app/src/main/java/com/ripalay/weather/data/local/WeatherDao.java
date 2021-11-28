package com.ripalay.weather.data.local;

import android.telecom.Call;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ripalay.weather.data.models.Weather;

@Dao
public interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Weather weather);

    @Query("SELECT * FROM weather")
    Weather getWeather();

}
