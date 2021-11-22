package com.ripalay.weather.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.ripalay.weather.App;
import com.ripalay.weather.common.Resource;
import com.ripalay.weather.data.models.Main;
import com.ripalay.weather.data.models.Sys;
import com.ripalay.weather.data.models.Weather;
import com.ripalay.weather.data.models.Weather__1;
import com.ripalay.weather.data.models.Wind;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    public MutableLiveData<Resource<Weather>> getTemp() {
        MutableLiveData<Resource<Weather>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        App.api.getTemp("Bishkek", "795bd94349391252f8e3b1fa191cfbb8", "metric").enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage(), null));
            }
        });
        return liveData;
    }
}
