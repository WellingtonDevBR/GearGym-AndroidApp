package com.gamezzar.geargymtest.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gamezzar.geargymtest.R;
import com.gamezzar.geargymtest.core.BaseFragment;
import com.gamezzar.geargymtest.databinding.LoginFragmentBinding;
import com.gamezzar.geargymtest.viewmodel.LoginViewModel;

public class LoginFragment extends BaseFragment {

    private LoginViewModel mViewModel;
    private LoginFragmentBinding binding;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = LoginFragmentBinding.inflate(inflater, container, false);
        binding.btnLogin.setOnClickListener(v -> NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_homeFragment));
        binding.tvSignUp.setOnClickListener(v -> NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_registerFragment));
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