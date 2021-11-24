package com.ripalay.weather.ui.select_sity_fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ripalay.weather.R;
import com.ripalay.weather.base.BaseFragment;
import com.ripalay.weather.databinding.FragmentSelectSityBinding;

import dagger.hilt.android.AndroidEntryPoint;

public class SelectSityFragment extends BaseFragment<FragmentSelectSityBinding> {


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

    @Override
    protected void setupUi() {
        binding.selectBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.cityEt.getText().toString() == null) {
                    Toast.makeText(requireContext(), "Название города не может быть пустым", Toast.LENGTH_SHORT).show();
                } else {
                    navController.navigate((NavDirections) SelectSityFragmentDirections.actionSelectSityFragmentToDemoFragment(
                            binding.cityEt.getText().toString()
                    ));
                }
            }
        });
    }

    @Override
    protected void setupObservers() {

    }
}