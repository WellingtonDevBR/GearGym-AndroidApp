package com.gamezzar.geargymtest.ui.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gamezzar.geargymtest.R;
import com.gamezzar.geargymtest.databinding.ProfileNavigationFragmentBinding;
import com.gamezzar.geargymtest.viewmodel.ProfileNavigationViewModel;

public class ProfileNavigationFragment extends Fragment {

    private ProfileNavigationViewModel mViewModel;
    private ProfileNavigationFragmentBinding binding;

    public static ProfileNavigationFragment newInstance() {
        return new ProfileNavigationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ProfileNavigationFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}