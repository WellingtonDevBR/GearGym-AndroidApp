package com.gamezzar.geargymtest.ui.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gamezzar.geargymtest.R;
import com.gamezzar.geargymtest.database.entities.Routine;
import com.gamezzar.geargymtest.database.entities.Set;
import com.gamezzar.geargymtest.database.entities.Workout;
import com.gamezzar.geargymtest.database.models.WorkoutWithSets;
import com.gamezzar.geargymtest.databinding.WorkoutSetupFragmentBinding;
import com.gamezzar.geargymtest.domain.SetModel;
import com.gamezzar.geargymtest.domain.WorkoutModel;
import com.gamezzar.geargymtest.seedwork.shared.BaseFragment;
import com.gamezzar.geargymtest.ui.adapter.WorkoutSetupAdapter;
import com.gamezzar.geargymtest.viewmodel.SharedWorkoutViewModel;
import com.gamezzar.geargymtest.viewmodel.WorkoutSetupViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WorkoutSetupFragment extends BaseFragment {

    private WorkoutSetupViewModel mViewModel;
    private WorkoutSetupFragmentBinding binding;
    private WorkoutSetupAdapter adapter;
    private SharedWorkoutViewModel sharedViewModel;
    private String selectedDay = null;

    public static WorkoutSetupFragment newInstance() {
        return new WorkoutSetupFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = WorkoutSetupFragmentBinding.inflate(inflater, container, false);
        setupRecyclerView();
        setOnWorkoutUpdatedListener();
        initializeWeekDays();
        setupViewModels();
        setupRecyclerView();
        setupObservers();
        binding.rvSelectedWorkouts.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupSaveRoutineAction();
    }

    @Override
    public void onResume() {
        super.onResume();
        hideFab();
        hideBottomAppBar();
    }

    private void initializeWeekDays() {
        String[] weekdays = getResources().getStringArray(R.array.weekdays);
        for (String day : weekdays) {
            TextView dayTextView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.day_view, binding.linearLayoutDaysOfWeek, false);
            dayTextView.setText(day);
            dayTextView.setOnClickListener(v -> {
                if (!v.isSelected()) {
                    clearDaySelection();
                    v.setSelected(true);
                    selectedDay = day;
                }
            });
            binding.linearLayoutDaysOfWeek.addView(dayTextView);
        }
    }
    private void clearDaySelection() {
        for (int i = 0; i < binding.linearLayoutDaysOfWeek.getChildCount(); i++) {
            binding.linearLayoutDaysOfWeek.getChildAt(i).setSelected(false);
        }
    }
    private void setupViewModels() {
        mViewModel = new ViewModelProvider(this).get(WorkoutSetupViewModel.class);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedWorkoutViewModel.class);
    }
    private void setupRecyclerView() {
        adapter = new WorkoutSetupAdapter(new ArrayList<>(), getChildFragmentManager());
        binding.rvSelectedWorkouts.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }
    private void setupObservers() {
        sharedViewModel.getSelectedWorkouts().observe(getViewLifecycleOwner(), workouts -> {
            updateUIBasedOnWorkouts(workouts);
            adapter.setWorkouts(workouts); // Update the RecyclerView with the new workouts
        });
    }
    private void setOnWorkoutUpdatedListener() {
        adapter.setOnWorkoutUpdatedListener(updatedWorkout -> {
            sharedViewModel.updateSelectedWorkout(updatedWorkout);
        });
    }
    private void updateUIBasedOnWorkouts(List<WorkoutModel> workouts) {
        boolean isSaveEnabled = workouts != null && !workouts.isEmpty() && workouts.stream().noneMatch(workout -> workout.getSets().isEmpty());
        binding.btnSave.setEnabled(isSaveEnabled);
        int buttonColor = isSaveEnabled ? R.color.primary : R.color.gray_200;
        binding.btnSave.setBackgroundColor(getResources().getColor(buttonColor, requireActivity().getTheme()));
    }
    private List<WorkoutWithSets> convertWorkoutsToEntities(List<WorkoutModel> workoutModels) {
        List<WorkoutWithSets> workoutsWithSets = new ArrayList<>();
        for (WorkoutModel workoutModel : workoutModels) {
            Workout workoutEntity = new Workout();
            workoutEntity.Name = workoutModel.getTitle();
            List<Set> sets = new ArrayList<>();
            for (SetModel setModel : workoutModel.getSets()) {
                Set setEntity = new Set();
                setEntity.Weight = Integer.parseInt(setModel.getKg());
                setEntity.Repetition = Integer.parseInt(setModel.getRepetitions());
                sets.add(setEntity);
            }
            WorkoutWithSets workoutWithSets = new WorkoutWithSets();
            workoutWithSets.workout = workoutEntity;
            workoutWithSets.sets = sets;
            workoutsWithSets.add(workoutWithSets);
        }
        return workoutsWithSets;
    }
    private void setupSaveRoutineAction() {
        binding.btnSave.setOnClickListener(v -> {
            String routineName = getRoutineName();
            if (!validateInputs(routineName)) {
                return;
            }

            Routine routine = createRoutine(routineName);
            List<WorkoutWithSets> workoutsWithSets = convertWorkoutsToEntities(
                    Objects.requireNonNull(sharedViewModel.getSelectedWorkouts().getValue())
            );

            mViewModel.saveRoutineWithWorkoutsAndSets(routine, workoutsWithSets);
            showToast("Your worked has been created successfully!");
            navigateToWorkoutFragment();
        });
    }
    private String getRoutineName() {
        return Objects.requireNonNull(binding.tilRoutineName.getEditText()).getText().toString().trim();
    }
    private boolean validateInputs(String routineName) {
        if (routineName.isEmpty()) {
            showToast("Please, enter the workout name.");
            return false;
        }
        if (selectedDay == null) {
            showToast("Please, select a day of the week.");
            return false;
        }
        if (sharedViewModel.getSelectedWorkouts().getValue() == null ||
                sharedViewModel.getSelectedWorkouts().getValue().isEmpty()) {
            showToast("Please, add at least one workout.");
            return false;
        }
        return true;
    }
    private Routine createRoutine(String routineName) {
        Routine routine = new Routine();
        routine.DayOfWeek = selectedDay;
        routine.Name = routineName;
        routine.UserId = 1;
        return routine;
    }
    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
    private void navigateToWorkoutFragment() {
        sharedViewModel.cleanSelectedWorkouts();
        NavHostFragment.findNavController(this).navigate(R.id.action_workoutSetupFragment_to_workoutFragment);
    }
}