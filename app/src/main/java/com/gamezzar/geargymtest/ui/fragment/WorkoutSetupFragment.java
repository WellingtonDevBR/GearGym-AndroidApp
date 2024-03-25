package com.gamezzar.geargymtest.ui.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gamezzar.geargymtest.R;
import com.gamezzar.geargymtest.databinding.WorkoutSetupFragmentBinding;
import com.gamezzar.geargymtest.domain.SetModel;
import com.gamezzar.geargymtest.domain.WorkoutModel;
import com.gamezzar.geargymtest.seedwork.shared.BaseFragment;
import com.gamezzar.geargymtest.seedwork.ui.CustomDialogFragment;
import com.gamezzar.geargymtest.ui.adapter.WorkoutSetupAdapter;
import com.gamezzar.geargymtest.viewmodel.SharedWorkoutViewModel;
import com.gamezzar.geargymtest.viewmodel.WorkoutSetupViewModel;

import java.util.ArrayList;
import java.util.List;

public class WorkoutSetupFragment extends BaseFragment {

    private WorkoutSetupViewModel mViewModel;
    private WorkoutSetupFragmentBinding binding;
    private WorkoutSetupAdapter adapter;
    private SharedWorkoutViewModel sharedWorkoutViewModel;
    private String selectedDay = null;

    public static WorkoutSetupFragment newInstance() {
        return new WorkoutSetupFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = WorkoutSetupFragmentBinding.inflate(inflater, container, false);
        binding.rvSelectedWorkouts.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new WorkoutSetupAdapter(new ArrayList<>(), getChildFragmentManager());
        adapter.setOnWorkoutUpdatedListener(updatedWorkout -> {
            sharedWorkoutViewModel.updateSelectedWorkout(updatedWorkout);
        });
        sharedWorkoutViewModel = new ViewModelProvider(requireActivity()).get(SharedWorkoutViewModel.class);
        sharedWorkoutViewModel.getSelectedWorkouts().observe(getViewLifecycleOwner(), newWorkouts -> {
            boolean enableSaveButton = false;
            int buttonColor = R.color.gray_200;
            if (newWorkouts != null && !newWorkouts.isEmpty()) {
                adapter.setWorkouts(newWorkouts);
                enableSaveButton = true;
                for (WorkoutModel workout : newWorkouts) {
                    if (workout.getSets() == null || workout.getSets().isEmpty()) {
                        enableSaveButton = false;
                        buttonColor = R.color.gray_200;
                        break;
                    }
                }
                if (enableSaveButton) {
                    buttonColor = R.color.primary;
                }
            }

            binding.btnSave.setEnabled(enableSaveButton);
            binding.btnSave.setBackgroundColor(getResources().getColor(buttonColor));
        });
        binding.btnSave.setOnClickListener(v -> {

        });
        binding.rvSelectedWorkouts.setAdapter(adapter);
        binding.rvSelectedWorkouts.setNestedScrollingEnabled(true);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WorkoutSetupViewModel.class);
        initializeWeekDays();
    }

    private void initializeWeekDays() {
        String[] weekdays = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        for (int i = 0; i < weekdays.length; i++) {
            TextView dayTextView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.day_view, binding.linearLayoutDaysOfWeek, false);
            dayTextView.setText(weekdays[i]);
            dayTextView.setOnClickListener(v -> {
                updateSelectedDay((TextView) v);
                this.selectedDay = ((TextView) v).getText().toString();
            });
            binding.linearLayoutDaysOfWeek.addView(dayTextView);
        }


    }

    private void updateSelectedDay(TextView selectedTextView) {
        for (int i = 0; i < binding.linearLayoutDaysOfWeek.getChildCount(); i++) {
            TextView child = (TextView) binding.linearLayoutDaysOfWeek.getChildAt(i);
            child.setSelected(false);
        }

        selectedTextView.setSelected(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        hideFab();
        hideBottomAppBar();
    }
}