package com.ripalay.weather.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.ripalay.weather.data.local.converters.MainCon;
import com.ripalay.weather.data.local.converters.WindCon;
import com.ripalay.weather.data.models.Weather;

import org.w3c.dom.CharacterData;

@Database(entities = Weather.class, version = 1)
@TypeConverters({MainCon.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract WeatherDao weatherDao();
}
