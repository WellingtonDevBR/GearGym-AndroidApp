package com.gamezzar.geargymtest.ui.fragment;

import static com.gamezzar.geargymtest.utils.UiUtils.updateButtonWithCounter;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.amazonaws.regions.Regions;
import com.gamezzar.geargymtest.BuildConfig;
import com.gamezzar.geargymtest.R;
import com.gamezzar.geargymtest.databinding.WorkoutListOptionFragmentBinding;
import com.gamezzar.geargymtest.seedwork.service.AWSS3Service;
import com.gamezzar.geargymtest.seedwork.shared.BaseFragment;
import com.gamezzar.geargymtest.domain.WorkoutModel;
import com.gamezzar.geargymtest.ui.adapter.WorkoutListOptionAdapter;
import com.gamezzar.geargymtest.utils.UiUtils;
import com.gamezzar.geargymtest.viewmodel.SharedWorkoutViewModel;
import com.gamezzar.geargymtest.viewmodel.WorkoutListOptionViewModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

public class WorkoutListOptionFragment extends BaseFragment {

    private WorkoutListOptionViewModel mViewModel;
    private SharedWorkoutViewModel sharedWorkoutViewModel;
    WorkoutListOptionAdapter adapter;
    private List<WorkoutModel> workoutList;
    private WorkoutListOptionFragmentBinding binding;
    private AWSS3Service awss3Service;
    private String currentBodyPart;

    public static WorkoutListOptionFragment newInstance() {
        return new WorkoutListOptionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initializeBinding(inflater, container);
        initializeDependencies();
        setUpCurrentBodyPartTitle();
        setupWorkoutList();
        setupSaveButton();
        return binding.getRoot();
    }

    public void setUpCurrentBodyPartTitle() {
        sharedWorkoutViewModel.getCurrentBodyPart().observe(getViewLifecycleOwner(), s -> {
            String modifiedString = s.substring(0, 1).toUpperCase() + s.substring(1);
            binding.tvWorkoutTypeLabel.setText(String.format(Locale.US, modifiedString + " Workout"));
            currentBodyPart = s;
        });
    }

    private void initializeBinding(LayoutInflater inflater, @Nullable ViewGroup container) {
        binding = WorkoutListOptionFragmentBinding.inflate(inflater, container, false);
    }

    private void initializeDependencies() {
        awss3Service = new AWSS3Service(requireContext(), BuildConfig.AWS_IDENTITY_POOL_ID, Regions.US_EAST_2);
        sharedWorkoutViewModel = new ViewModelProvider(requireActivity()).get(SharedWorkoutViewModel.class);
    }

    private void setupWorkoutList() {
        workoutList = new ArrayList<>();
        adapter = new WorkoutListOptionAdapter(workoutList, this::updateCounter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
    }

    private void updateCounter() {
        sharedWorkoutViewModel.getWorkoutCounter().observe(getViewLifecycleOwner(), counter -> {
            UiUtils.updateButtonWithCounter(requireContext(), binding.btnSave, counter, R.drawable.counter_circle);
        });
    }

    private void setupSaveButton() {
        Button btnSave = binding.btnSave;
        btnSave.setOnClickListener(v -> saveWorkouts());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        observeSelectedWorkouts();
        loadWorkoutsFromArguments();
        adapter.notifyDataSetChanged();
    }


    private void observeSelectedWorkouts() {
        sharedWorkoutViewModel.getSelectedWorkouts().observe(getViewLifecycleOwner(), selectedWorkouts -> updateButtonWithCounter(requireContext(), binding.btnSave, selectedWorkouts.size(), R.drawable.counter_circle));
    }

    private void loadWorkoutsFromArguments() {
        if (getArguments() != null) {
            WorkoutModel[] workoutsArray = WorkoutListOptionFragmentArgs.fromBundle(getArguments()).getWorkouts();
            fetchWorkoutsDetails(workoutsArray);
        }
    }

    private void fetchWorkoutsDetails(WorkoutModel[] workoutsArray) {
        List<WorkoutModel> currentSelectedWorkouts = sharedWorkoutViewModel.getSelectedWorkouts().getValue();
        if (currentSelectedWorkouts == null) return;

        Set<String> selectedTitles = new HashSet<>();
        for (WorkoutModel selectedWorkout : currentSelectedWorkouts) {
            selectedTitles.add(selectedWorkout.getTitle());
        }

        for (WorkoutModel workout : workoutsArray) {
            String bucketName = "geargym";
            String objectKey = "workout/" + workout.getImageName();

            workout.setChecked(selectedTitles.contains(workout.getTitle()));
            awss3Service.getObjectUrl(bucketName, objectKey, new AWSS3Service.UrlCallback() {
                @Override
                public void onUrlReady(String imageUrl) {
                    workout.setImageUrl(imageUrl);
                    workoutList.add(workout);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onError(Exception e) {
                    Log.e("WorkoutListOptionFragment", "Error fetching workout details", e);
                }
            });
        }
    }

    private void saveWorkouts() {
        boolean hasCheckedItems = false;

        for (WorkoutModel workout : workoutList) {
            if (workout.getChecked()) {
                sharedWorkoutViewModel.addSelectedWorkout(workout);
                hasCheckedItems = true; // Found at least one item to navigate with
            } else {
                sharedWorkoutViewModel.removeSelectedWorkout(workout);
            }
        }

        if (hasCheckedItems) {
            NavHostFragment.findNavController(this).navigate(R.id.action_workoutListOptionFragment_to_bodyPartListFragment);
            Toast.makeText(getContext(), "Workouts saved successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Please select at least one workout to save.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        hideInterfaceElements();
        restoreCounterState();
    }

    private void hideInterfaceElements() {
        hideBottomAppBar();
        hideFab();
    }

    private void restoreCounterState() {
        List<WorkoutModel> selectedWorkouts = sharedWorkoutViewModel.getSelectedWorkouts().getValue();
        if (selectedWorkouts != null) {
            updateButtonWithCounter(requireContext(), binding.btnSave, selectedWorkouts.size(), R.drawable.counter_circle);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}