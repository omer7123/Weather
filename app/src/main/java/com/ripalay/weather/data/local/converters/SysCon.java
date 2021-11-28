package com.ripalay.weather.data.local.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ripalay.weather.data.models.Clouds;
import com.ripalay.weather.data.models.Sys;

import java.lang.reflect.Type;

public class SysCon {
    @TypeConverter
    public String fromSysToString(Sys sys) {
        if (sys == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Sys>() {
        }.getType();
        return gson.toJson(sys, type);
    }

    @TypeConverter
    public Sys fromStringToSys(String sysString) {
        if (sysString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Sys>() {
        }.getType();
        return gson.fromJson(sysString, type);
    }

}
