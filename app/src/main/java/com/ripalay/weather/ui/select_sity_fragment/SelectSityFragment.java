package com.ripalay.weather.ui.select_sity_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ripalay.weather.R;
import com.ripalay.weather.base.BaseFragment;
import com.ripalay.weather.databinding.FragmentSelectSityBinding;

import dagger.hilt.android.AndroidEntryPoint;

public class SelectSityFragment extends BaseFragment<FragmentSelectSityBinding> implements OnMapReadyCallback {

    private GoogleMap mMap;
    private NavController navController;

    @Override
    protected FragmentSelectSityBinding bind() {
        return FragmentSelectSityBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        navController = navHostFragment.getNavController();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_select_sity, null, false);
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.selectMap);
        mapFragment.getMapAsync(this);

        return view;

    }

    @Override
    protected void setupUi() {

    }

    @Override
    protected void setupObservers() {

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                MarkerOptions options = new MarkerOptions();
                options.position(latLng);
                mMap.addMarker(options);
            }
        });
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                LatLng latLng =  marker.getPosition();
                String lat = String.valueOf(latLng.latitude);
                String lon = String.valueOf(latLng.longitude);
                navController.navigate((NavDirections) SelectSityFragmentDirections.actionSelectSityFragmentToDemoFragment(
                        lat,lon
                ));

                return true;
            }
        });
    }
}