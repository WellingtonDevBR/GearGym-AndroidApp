package com.gamezzar.geargymtest.feature.login;

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
import com.gamezzar.geargymtest.databinding.RegisterFragmentBinding;

public class RegisterFragment extends BaseFragment {

    private RegisterViewModel mViewModel;
    private RegisterFragmentBinding binding;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = RegisterFragmentBinding.inflate(inflater, container, false);
        binding.ibReturn.setOnClickListener(v -> NavHostFragment.findNavController(this).navigate(R.id.action_registerFragment_to_loginFragment));
        binding.btnRegister.setOnClickListener(v -> NavHostFragment.findNavController(this).navigate(R.id.action_registerFragment_to_HomeFragment));
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        hideToolBar();
        hideBottomAppBar();
        hideFab();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}