package com.ripalay.weather.ui.demo_fragment;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.ripalay.weather.base.BaseFragment;
import com.ripalay.weather.common.Resource;
import com.ripalay.weather.data.models.Main;
import com.ripalay.weather.data.models.Sys;
import com.ripalay.weather.data.models.Weather;
import com.ripalay.weather.data.models.Weather__1;
import com.ripalay.weather.data.models.Wind;
import com.ripalay.weather.databinding.FragmentDemoBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DemoFragment extends BaseFragment<FragmentDemoBinding> {
    private DemoViewModel viewModel;
    private Main main;
    private Wind wind;
    private Sys sys;
    private ArrayList<Weather__1> weather__1 = new ArrayList<>();


    @Override
    protected FragmentDemoBinding bind() {
        return FragmentDemoBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupUi() {

        viewModel = new ViewModelProvider(requireActivity()).get(DemoViewModel.class);
        viewModel.fetchTemp();
        viewModel.fetchWind();
        viewModel.fetchSys();
        viewModel.fethWeather();
        long i;
        i = System.currentTimeMillis();
        //DateFormat
    }

    @Override
    protected void setupObservers() {
        viewModel.tempLiveData.observe(getViewLifecycleOwner(), new Observer<Resource<Weather<Main>>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(Resource<Weather<Main>> response) {
                switch (response.status) {
                    case SUCCESS: {
                        main = response.data.getMain();
                        binding.tempnowTv.setText(String.valueOf((int) Math.round(main.getTemp())));
                        binding.tempminTv.setText(String.valueOf((int) Math.round(main.getTempMin())));
                        binding.tempmaxTv.setText(String.valueOf((int) Math.round(main.getTempMax())));
                        binding.hummTv.setText(main.getHumidity() + "%");
                        binding.pressTv.setText(main.getPressure() + "mBar");
                        break;
                    }
                    case ERROR: {
                        break;
                    }
                    case LOADING: {
                        binding.progress.setVisibility(View.VISIBLE);

                        break;
                    }
                }
            }
        });

        viewModel.windLiveData.observe(getViewLifecycleOwner(), new Observer<Resource<Weather<Wind>>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(Resource<Weather<Wind>> response) {
                switch (response.status) {
                    case SUCCESS: {
                        wind = response.data.getWind();
                        binding.windTv.setText((int) Math.round(wind.getSpeed()) + " m/ s");
                        break;
                    }
                    case ERROR: {
                        break;
                    }
                    case LOADING: {
                        binding.progress.setVisibility(View.VISIBLE);

                        break;
                    }
                }
            }
        });
        viewModel.sysLiveData.observe(getViewLifecycleOwner(), new Observer<Resource<Weather<Sys>>>() {
            @Override
            public void onChanged(Resource<Weather<Sys>> response) {
                switch (response.status) {
                    case SUCCESS: {
                        sys = response.data.getSys();
                        Long sunrise = Long.valueOf(sys.getSunrise());
                        Long sunset = Long.valueOf(sys.getSunset());

                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(sunset);


                        Calendar calendarSunrise = Calendar.getInstance();
                        calendarSunrise.setTimeInMillis(sunrise);

                        SimpleDateFormat df = new SimpleDateFormat("hh:MM");

                        String dateSunrise = df.format(calendarSunrise.getTime());
                        String dateSunset = df.format(calendar.getTime());

                        binding.sunriseTv.setText(dateSunrise);
                        binding.sunsetTv.setText(dateSunset);


                        break;
                    }
                    case ERROR: {
                        break;
                    }
                    case LOADING: {
                        binding.progress.setVisibility(View.VISIBLE);

                        break;
                    }
                }
            }

        });
        viewModel.weatherLiveData.observe(getViewLifecycleOwner(), new Observer<Resource<Weather<Weather__1>>>() {
            @Override
            public void onChanged(Resource<Weather<Weather__1>> response) {
                switch (response.status) {
                    case SUCCESS:

                        binding.progress.setVisibility(View.GONE);
                        weather__1 = (ArrayList<Weather__1>) response.data.getWeather();
                        binding.weatherNow.setText(weather__1.get(0).getMain());
                        Glide.with(requireContext())
                                .load("https://openweathermap.org/img/wn/" + weather__1.get(0).getIcon() + ".png")
                                .into(binding.iconWeath);
                        break;
                    case ERROR:

                        break;
                    case LOADING:
                        binding.progress.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
    }


}