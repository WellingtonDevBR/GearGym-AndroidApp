package com.gamezzar.geargymtest.ui.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gamezzar.geargymtest.R;
import com.gamezzar.geargymtest.seedwork.shared.BaseFragment;
import com.gamezzar.geargymtest.databinding.HomeFragmentBinding;
import com.gamezzar.geargymtest.viewmodel.HomeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class HomeFragment extends BaseFragment {
    private HomeViewModel mViewModel;
    private AppCompatActivity activity;
    private static final String[] REQUIRED_PERMISSIONS = new String[]{Manifest.permission.CAMERA};

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    private HomeFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = HomeFragmentBinding.inflate(inflater, container, false);
        binding.ivProfile.setOnClickListener(v -> openProfileNavigation());
        activity = (AppCompatActivity) getActivity();
        assert activity != null;
        FloatingActionButton floatingActionButton = activity.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(v -> {
            if (allPermissionsGranted()) {
                openCameraRecognition();
            } else {
                requestCameraPermission();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
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

    private void requestCameraPermission() {
        showRationaleDialog();
    }

    private void openAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", requireActivity().getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

    private void showRationaleDialog() {
        new AlertDialog.Builder(getContext()).setTitle("Camera Permission Needed").setMessage("Something went wrong while accessing your camera. Allow the use of your camera.").setPositiveButton("App Settings", (dialog, which) -> {
            openAppSettings();
        }).setNegativeButton("Cancel", (dialog, which) -> {
            dialog.dismiss();
        }).create().show();
    }

    private boolean allPermissionsGranted() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(requireContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private void navigateToWorkout() {
        NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_workoutFragment);
    }

    private void openProfileNavigation() {
        NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_profileNavFragment);
    }

    private void openCameraRecognition() {
        NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_cameraRecognitionFragment);
    }
}