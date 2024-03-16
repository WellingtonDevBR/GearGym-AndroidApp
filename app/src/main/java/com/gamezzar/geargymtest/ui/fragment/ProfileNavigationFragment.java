package com.gamezzar.geargymtest.ui.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gamezzar.geargymtest.R;
import com.gamezzar.geargymtest.core.BaseFragment;
import com.gamezzar.geargymtest.databinding.ProfileNavigationFragmentBinding;
import com.gamezzar.geargymtest.viewmodel.ProfileNavigationViewModel;
import com.google.android.material.bottomappbar.BottomAppBar;

public class ProfileNavigationFragment extends BaseFragment {

    private ProfileNavigationViewModel mViewModel;
    private ProfileNavigationFragmentBinding binding;

    public static ProfileNavigationFragment newInstance() {
        return new ProfileNavigationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ProfileNavigationFragmentBinding.inflate(inflater, container, false);
        binding.ibClose.setOnClickListener(v -> closeProfileNavigation());
        binding.tvLogout.setOnClickListener(v -> exitApplication());
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        hideToolBar();
        hideBottomAppBar();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hideToolBar();
        binding = null;
    }

    public void closeProfileNavigation() {
        NavHostFragment.findNavController(this).navigate(R.id.action_profileNavFragment_to_homeFragment);
    }

    public void exitApplication() {
        NavHostFragment.findNavController(this).navigate(R.id.action_profileNavFragment_to_loginFragment);
    }
}