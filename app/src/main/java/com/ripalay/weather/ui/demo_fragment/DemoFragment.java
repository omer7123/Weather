package com.ripalay.weather.ui.demo_fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.model.LatLng;
import com.ripalay.weather.R;
import com.ripalay.weather.base.BaseFragment;
import com.ripalay.weather.common.Resource;
import com.ripalay.weather.data.local.WeatherDao;
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

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.HiltAndroidApp;

@AndroidEntryPoint
public class DemoFragment extends BaseFragment<FragmentDemoBinding> implements LocationListener {
    private DemoViewModel viewModel;
    private DemoFragmentArgs args;
    private NavController navController;
    private Main main;
    private Wind wind;
    private Weather weather;
    private Sys sys;
    Double lat;
    Double lon;
    private ArrayList<Weather__1> weather__1 = new ArrayList<>();
private boolean succefull = false;
    private final String[] perms = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            // Manifest.permission.ACCESS_COARSE_LOCATION
    };
    private LocationManager locationManager;
    @Inject
    WeatherDao dao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        navController = navHostFragment.getNavController();
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        ActivityCompat.requestPermissions(requireActivity(), perms, 1);

        if (getArguments() != null) {
            args = DemoFragmentArgs.fromBundle(getArguments());
            lat = Double.parseDouble(args.getLat());
            lon = Double.parseDouble(args.getLon());
            Log.e("TAG", "onCreate:" + lat );
        }


    }

    @Override
    protected FragmentDemoBinding bind() {
        return FragmentDemoBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupUi() {
        getCurrentLocation();
        binding.cityTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_demoFragment_to_selectSityFragment);
            }
        });
        viewModel = new ViewModelProvider(requireActivity()).get(DemoViewModel.class);


        viewModel.setLat(lat);
        viewModel.setLon(lon);
        viewModel.fetchTemp();

    }

    @Override
    protected void setupObservers() {
        getInfo();
    }

    private void setResInUi() {
        binding.weatherNow.setText(weather__1.get(0).getMain());
        Glide.with(requireContext())
                .load("https://openweathermap.org/img/wn/" + weather__1.get(0).getIcon() + ".png")
                .override(100, 100)
                .into(binding.iconWeath);

        binding.windTv.setText((int) Math.round(wind.getSpeed()) + " m/ s");
        binding.cityTv.setText(weather.getName());
        binding.tempnowTv.setText(String.valueOf((int) Math.round(main.getTemp())));
        binding.tempminTv.setText(String.valueOf((int) Math.round(main.getTempMin())));
        binding.tempmaxTv.setText(String.valueOf((int) Math.round(main.getTempMax())));
        binding.hummTv.setText(main.getHumidity() + "%");
        binding.pressTv.setText(main.getPressure() + "mBar");
        binding.progress.setVisibility(View.GONE);

        SimpleDateFormat df = new SimpleDateFormat("HH:mm");


        binding.sunriseTv.setText(df.format(sys.getSunrise()));
        binding.sunsetTv.setText(df.format(sys.getSunset()));

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Log.e("TAG", "onLocationChanged: " + lat + " bool"+succefull  );
        if(!succefull && lat==42.0 && lon ==100.0) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            Log.e("ololo", latLng.toString());
            lat = location.getLatitude();
            lon = location.getLongitude();
            viewModel.setLat(lat);
            viewModel.setLon(lon);
            viewModel.fetchTemp();
            getInfo();
            succefull = true;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
//                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            ) {
                getCurrentLocation();

            } else {
                ActivityCompat.requestPermissions(requireActivity(), perms, 1);

            }
        }
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
//                ActivityCompat.checkSelfPermission(this,
//                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0, 0,
                    this
            );
        }
    }

    private void getInfo() {
        viewModel.tempLiveData.observe(getViewLifecycleOwner(), new Observer<Resource<Weather>>() {
            @Override
            public void onChanged(Resource<Weather> response) {
                switch (response.status) {
                    case SUCCESS: {
                        main = response.data.getMain();
                        weather = response.data;
                        wind = response.data.getWind();
                        sys = response.data.getSys();
                        weather__1 = (ArrayList<Weather__1>) response.data.getWeather();
                        setResInUi();

                        break;
                    }
                    case ERROR: {
                        Toast.makeText(requireContext(), "Ошибка с доступом в интернет. Загружена последняя информация", Toast.LENGTH_LONG).show();
                        main = dao.getWeather().getMain();
                        weather = dao.getWeather();
                        wind = dao.getWeather().getWind();
                        sys = dao.getWeather().getSys();
                        weather__1 = (ArrayList<Weather__1>) dao.getWeather().getWeather();
                        setResInUi();
                        binding.progress.setVisibility(View.GONE);
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
