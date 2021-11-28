package com.ripalay.weather.data.local;

import android.content.Context;

import androidx.room.Room;

public class RoomClient {

    public AppDatabase provideDatabase(Context context) {
        return Room.databaseBuilder(
                context,
                AppDatabase.class,
                "weather_database"
        )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

}
