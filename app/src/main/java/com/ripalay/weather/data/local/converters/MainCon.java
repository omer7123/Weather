package com.ripalay.weather.data.local.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ripalay.weather.data.models.Clouds;
import com.ripalay.weather.data.models.Main;

import java.lang.reflect.Type;

public class MainCon {



    @TypeConverter
    public String fromMainToString(Main main) {
        if (main == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Main>() {}.getType();
        return gson.toJson(main, type);
    }

    @TypeConverter
    public Main fromStringToMain(String mainString) {
        if (mainString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Main>() {}.getType();
        return gson.fromJson(mainString, type);
    }

}
