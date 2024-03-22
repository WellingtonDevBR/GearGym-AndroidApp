package com.gamezzar.geargymtest.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amazonaws.regions.Regions;
import com.gamezzar.geargymtest.BuildConfig;
import com.gamezzar.geargymtest.databinding.WorkoutListOptionFragmentBinding;
import com.gamezzar.geargymtest.seedwork.service.AWSS3Service;
import com.gamezzar.geargymtest.seedwork.shared.BaseFragment;
import com.gamezzar.geargymtest.domain.WorkoutModel;
import com.gamezzar.geargymtest.ui.adapter.WorkoutListOptionAdapter;
import com.gamezzar.geargymtest.viewmodel.WorkoutListOptionViewModel;

import java.util.ArrayList;
import java.util.List;

public class WorkoutListOptionFragment extends BaseFragment {

    private WorkoutListOptionViewModel mViewModel;
    WorkoutListOptionAdapter adapter;
    private List<WorkoutModel> workoutList;
    private WorkoutListOptionFragmentBinding binding;
    private AWSS3Service awss3Service;

    public static WorkoutListOptionFragment newInstance() {
        return new WorkoutListOptionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = WorkoutListOptionFragmentBinding.inflate(inflater, container, false);
        awss3Service = new AWSS3Service(requireContext(), BuildConfig.AWS_IDENTITY_POOL_ID, Regions.US_EAST_2);
        workoutList = new ArrayList<>();

        adapter = new WorkoutListOptionAdapter(workoutList);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            WorkoutModel[] workoutsArray = WorkoutListOptionFragmentArgs.fromBundle(getArguments()).getWorkouts();
            for (WorkoutModel workout : workoutsArray) {
                String bucketName = "geargym";
                String objectKey = "workout/" + workout.getImageUrl();
                awss3Service.getObjectUrl(bucketName, objectKey, new AWSS3Service.UrlCallback() {

                    @Override
                    public void onUrlReady(String imageUrl) {
                        workout.setImageUrl(imageUrl);
                        System.out.println(workout.getImageUrl());
                        workoutList.add(workout);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        hideBottomAppBar();
        hideFab();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}