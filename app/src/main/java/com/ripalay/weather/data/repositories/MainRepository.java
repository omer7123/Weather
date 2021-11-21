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

    public MutableLiveData<Resource<Weather<Main>>> getTemp() {
        MutableLiveData<Resource<Weather<Main>>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        App.api.getTemp("Bishkek", "795bd94349391252f8e3b1fa191cfbb8", "metric").enqueue(new Callback<Weather<Main>>() {
            @Override
            public void onResponse(Call<Weather<Main>> call, Response<Weather<Main>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));
                }
            }

            @Override
            public void onFailure(Call<Weather<Main>> call, Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage(), null));
            }
        });
        return liveData;
    }

    public MutableLiveData<Resource<Weather<Wind>>> getWind() {
        MutableLiveData<Resource<Weather<Wind>>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        App.api.getWind("Bishkek", "795bd94349391252f8e3b1fa191cfbb8", "metric").enqueue(new Callback<Weather<Wind>>() {
            @Override
            public void onResponse(Call<Weather<Wind>> call, Response<Weather<Wind>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));
                }
            }

            @Override
            public void onFailure(Call<Weather<Wind>> call, Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage(), null));

            }
        });
        return liveData;

    }

    public MutableLiveData<Resource<Weather<Sys>>> getSys() {
        MutableLiveData<Resource<Weather<Sys>>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        App.api.getCountryInfo("Bishkek", "795bd94349391252f8e3b1fa191cfbb8", "metric").enqueue(new Callback<Weather<Sys>>() {
            @Override
            public void onResponse(Call<Weather<Sys>> call, Response<Weather<Sys>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));
                }
            }

            @Override
            public void onFailure(Call<Weather<Sys>> call, Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage(), null));
            }
        });
        return liveData;
    }
    public MutableLiveData<Resource<Weather<Weather__1>>> getBossInfo() {
        MutableLiveData<Resource<Weather<Weather__1>>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        App.api.getBossInfo("Bishkek", "795bd94349391252f8e3b1fa191cfbb8", "metric").enqueue(new Callback<Weather<Weather__1>>() {
            @Override
            public void onResponse(Call<Weather<Weather__1>> call, Response<Weather<Weather__1>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));
                }
            }

            @Override
            public void onFailure(Call<Weather<Weather__1>> call, Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage(), null));
            }
        });
        return liveData;
    }
}
