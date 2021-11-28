package com.ripalay.weather.data.local.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ripalay.weather.data.models.Clouds;
import com.ripalay.weather.data.models.Weather__1;

import java.lang.reflect.Type;
import java.util.List;

public class Weathe_1Con {

    @TypeConverter
    public String fromWeatherToString(List<Weather__1> weather__1) {
        if (weather__1 == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Weather__1>>() {}.getType();
        return gson.toJson(weather__1, type);
    }

    @TypeConverter
    public List<Weather__1> fromStringToWeath(String weather_1String) {
        if (weather_1String == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Weather__1>>() {}.getType();
        return gson.fromJson(weather_1String, type);
    }

}
