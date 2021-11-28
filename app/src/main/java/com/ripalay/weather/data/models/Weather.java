
package com.ripalay.weather.data.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ripalay.weather.data.local.Converter;
import com.ripalay.weather.data.local.converters.CloudCon;
import com.ripalay.weather.data.local.converters.MainCon;
import com.ripalay.weather.data.local.converters.SysCon;
import com.ripalay.weather.data.local.converters.Weathe_1Con;
import com.ripalay.weather.data.local.converters.WindCon;

@Entity
public class Weather {

//    @SerializedName("coord")
//    @Expose
//    private Coord coord;
    @SerializedName("weather")
    @Expose
    @TypeConverters({Weathe_1Con.class})
    private List<Weather__1> weather = null;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("main")
    @Expose
    @TypeConverters({MainCon.class})
    private Main main;
    @SerializedName("visibility")
    @Expose
    private Integer visibility;
    @SerializedName("wind")
    @Expose
    @TypeConverters({WindCon.class})
    private Wind wind;
    @SerializedName("clouds")
    @Expose
    @TypeConverters({CloudCon.class})
    private Clouds clouds;
    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("sys")
    @Expose
    @TypeConverters({SysCon.class})
    private Sys sys;
    @SerializedName("timezone")
    @Expose
    private Integer timezone;
    @SerializedName("id")
    @Expose
    @PrimaryKey
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cod")
    @Expose
    private Integer cod;

//    public Coord getCoord() {
//        return coord;
//    }
//
//    public void setCoord(Coord coord) {
//        this.coord = coord;
//    }

    public List<Weather__1> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather__1> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

}
