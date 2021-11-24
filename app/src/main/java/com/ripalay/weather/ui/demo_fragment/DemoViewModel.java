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

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DemoViewModel extends ViewModel {
    private String city;

    private MainRepository repository;
    public LiveData<Resource<Weather>> tempLiveData;

    public void setCity(String city) {
        this.city = city;
    }

    @Inject
    public DemoViewModel(MainRepository repository) {
        this.repository = repository;
    }


    public void fetchTemp() {
        repository.setCity(city);
        tempLiveData = repository.getTemp();
    }

}
