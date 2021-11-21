package com.ripalay.weather.ui.demo_fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ripalay.weather.common.Resource;
import com.ripalay.weather.data.models.Main;
import com.ripalay.weather.data.models.Sys;
import com.ripalay.weather.data.models.Weather;
import com.ripalay.weather.data.models.Weather__1;
import com.ripalay.weather.data.models.Wind;
import com.ripalay.weather.data.repositories.MainRepository;

public class DemoViewModel extends ViewModel {

    private MainRepository repository;
    public LiveData<Resource<Weather<Main>>> tempLiveData;
    public LiveData<Resource<Weather<Wind>>> windLiveData;
    public LiveData<Resource<Weather<Sys>>> sysLiveData;
    public LiveData<Resource<Weather<Weather__1>>> weatherLiveData;

    public DemoViewModel() {
        this.repository = new MainRepository();
    }

    public void fetchTemp() {
        tempLiveData = repository.getTemp();
    }

    public void fetchWind() {
        windLiveData = repository.getWind();
    }

    public void fetchSys() {
        sysLiveData = repository.getSys();
    }

    public void fethWeather() {
        weatherLiveData = repository.getBossInfo();
    }

}
