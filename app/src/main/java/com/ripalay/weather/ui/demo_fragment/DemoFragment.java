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
    private Weather weather;
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

    }

    @Override
    protected void setupObservers() {
        viewModel.tempLiveData.observe(getViewLifecycleOwner(), new Observer<Resource<Weather>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(Resource<Weather> response) {
                switch (response.status) {
                    case SUCCESS: {
                        main = response.data.getMain();
                        weather = response.data;
                        wind = response.data.getWind();
                        weather__1 = (ArrayList<Weather__1>) response.data.getWeather();
                        binding.weatherNow.setText(weather__1.get(0).getMain());
                        Glide.with(requireContext())
                                .load("https://openweathermap.org/img/wn/" + weather__1.get(0).getIcon() + ".png")
                                .into(binding.iconWeath);

                        binding.windTv.setText((int) Math.round(wind.getSpeed()) + " m/ s");
                        binding.cityTv.setText(weather.getName());
                        binding.tempnowTv.setText(String.valueOf((int) Math.round(main.getTemp())));
                        binding.tempminTv.setText(String.valueOf((int) Math.round(main.getTempMin())));
                        binding.tempmaxTv.setText(String.valueOf((int) Math.round(main.getTempMax())));
                        binding.hummTv.setText(main.getHumidity() + "%");
                        binding.pressTv.setText(main.getPressure() + "mBar");
                        binding.progress.setVisibility(View.GONE);


                        sys = response.data.getSys();


                        SimpleDateFormat df = new SimpleDateFormat("hh:MM");


                        binding.sunriseTv.setText(df.format(sys.getSunrise()));

                        binding.sunsetTv.setText(df.format(sys.getSunset()));

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
    }
}
