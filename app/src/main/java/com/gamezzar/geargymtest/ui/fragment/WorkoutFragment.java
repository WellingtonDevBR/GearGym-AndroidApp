package com.gamezzar.geargymtest.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gamezzar.geargymtest.R;
import com.gamezzar.geargymtest.seedwork.shared.BaseFragment;
import com.gamezzar.geargymtest.databinding.WorkoutFragmentBinding;
import com.gamezzar.geargymtest.viewmodel.WorkoutViewModel;

public class WorkoutFragment extends BaseFragment {

    private WorkoutViewModel mViewModel;
    private WorkoutFragmentBinding binding;

    public static WorkoutFragment newInstance() {
        return new WorkoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = WorkoutFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.createWorkoutCard.setOnClickListener(v -> navigateToCreateWorkout());
        binding.listWorkoutCard.setOnClickListener(v -> navigateToListWorkout());
    }

    @Override
    public void onResume() {
        super.onResume();
        hideBottomAppBar();
        showToolBar();
    }

    private void navigateToCreateWorkout() {
        NavHostFragment.findNavController(this).navigate(R.id.action_workoutFragment_to_createWorkoutFragment);
    }

    private void navigateToListWorkout() {
        NavHostFragment.findNavController(this).navigate((R.id.action_workoutFragment_to_workoutListFragment));
    }
}