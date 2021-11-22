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
    public LiveData<Resource<Weather>> tempLiveData;

    public DemoViewModel() {
        this.repository = new MainRepository();
    }

    public void fetchTemp() {
        tempLiveData = repository.getTemp();
    }

}
