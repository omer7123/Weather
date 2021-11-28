package com.ripalay.weather.data.local.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ripalay.weather.data.models.Clouds;

import java.lang.reflect.Type;

public class CloudCon {
    @TypeConverter
    public String fromCloudsToString(Clouds clouds) {
        if (clouds == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Clouds>() {
        }.getType();
        return gson.toJson(clouds, type);
    }

    @TypeConverter
    public Clouds fromStringToClouds(String cloudsString) {
        if (cloudsString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Clouds>() {
        }.getType();
        return gson.fromJson(cloudsString, type);
    }


}
