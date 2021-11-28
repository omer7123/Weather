package com.ripalay.weather.data.local.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ripalay.weather.data.models.Clouds;
import com.ripalay.weather.data.models.Wind;

import java.lang.reflect.Type;

public class WindCon {
    @TypeConverter
    public String fromWindToString(Wind wind) {
        if (wind == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Wind>() {
        }.getType();
        return gson.toJson(wind, type);
    }

    @TypeConverter
    public Wind fromStringToWind(String windString) {
        if (windString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Wind>() {}.getType();
        return gson.fromJson(windString, type);
    }

}
