package com.gamezzar.geargymtest.ui.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gamezzar.geargymtest.R;
import com.gamezzar.geargymtest.core.BaseFragment;
import com.gamezzar.geargymtest.databinding.HomeFragmentBinding;
import com.gamezzar.geargymtest.viewmodel.HomeViewModel;

public class HomeFragment extends BaseFragment {

    private HomeViewModel mViewModel;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    private HomeFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment with ViewBinding
        binding = HomeFragmentBinding.inflate(inflater, container, false);
        binding.ivProfile.setOnClickListener(v -> openProfileNavigation());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // Now use ViewBinding to set a click listener
        binding.workoutCard.setOnClickListener(v -> navigateToWorkout());
    }

    @Override
    public void onResume() {
        super.onResume();
        hideToolBar();
        showBottomAppBar();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void navigateToWorkout() {
        NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_workoutFragment);
    }

    private void openProfileNavigation() {
        NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_profileNavFragment);
    }
}