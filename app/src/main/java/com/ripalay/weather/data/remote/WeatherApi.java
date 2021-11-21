package com.ripalay.weather.data.remote;

import com.ripalay.weather.data.models.Main;
import com.ripalay.weather.data.models.Sys;
import com.ripalay.weather.data.models.Weather;
import com.ripalay.weather.data.models.Weather__1;
import com.ripalay.weather.data.models.Wind;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("weather?")
    Call<Weather<Main>> getTemp(
            @Query("q") String city,
            @Query("appid") String appId,
            @Query("units") String metric
    );

    @GET("weather?")
    Call<Weather<Wind>> getWind(
            @Query("q") String city,
            @Query("appid") String appId,
            @Query("units") String metric
    );

    @GET("weather?")
    Call<Weather<Sys>> getCountryInfo(
            @Query("q") String city,
            @Query("appid") String appId,
            @Query("units") String metric
    );
    @GET("weather?")
    Call<Weather<Weather__1>> getBossInfo(
            @Query("q") String city,
            @Query("appid") String appId,
            @Query("units") String metric
    );
}
