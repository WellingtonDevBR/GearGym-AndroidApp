package com.gamezzar.geargymtest.ui.fragment;

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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.amazonaws.regions.Regions;
import com.gamezzar.geargymtest.BuildConfig;
import com.gamezzar.geargymtest.R;
import com.gamezzar.geargymtest.database.entities.Workout;
import com.gamezzar.geargymtest.database.models.BodyPartWithWorkouts;
import com.gamezzar.geargymtest.databinding.BodyPartFragmentBinding;
import com.gamezzar.geargymtest.domain.BodyPartModel;
import com.gamezzar.geargymtest.seedwork.shared.BaseFragment;
import com.gamezzar.geargymtest.domain.WorkoutModel;
import com.gamezzar.geargymtest.seedwork.service.AWSS3Service;
import com.gamezzar.geargymtest.ui.adapter.BodyPartAdapter;
import com.gamezzar.geargymtest.viewmodel.NewWorkoutViewModel;
import com.gamezzar.geargymtest.viewmodel.SharedWorkoutViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class BodyPartFragment extends BaseFragment {

    private NewWorkoutViewModel mViewModel;
    private SharedWorkoutViewModel sharedWorkoutViewModel;
    private BodyPartAdapter adapter;
    private List<BodyPartModel> bodyPartModelList;
    private List<WorkoutModel> workoutList;
    private BodyPartFragmentBinding binding;
    private AWSS3Service awss3Service;

    public static BodyPartFragment newInstance() {
        return new BodyPartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BodyPartFragmentBinding.inflate(inflater, container, false);
        awss3Service = new AWSS3Service(requireContext(), BuildConfig.AWS_IDENTITY_POOL_ID, Regions.US_EAST_2);
        bodyPartModelList = new ArrayList<>();
        workoutList = new ArrayList<>();
        sharedWorkoutViewModel = new ViewModelProvider(requireActivity()).get(SharedWorkoutViewModel.class);
        adapter = new BodyPartAdapter(bodyPartModelList, this::onBodyPartClicked);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);

        return binding.getRoot();
    }

    private void onBodyPartClicked(BodyPartModel bodyPart) {
        WorkoutModel[] workoutList = bodyPart.workoutList().toArray(new WorkoutModel[0]);
        BodyPartFragmentDirections.ActionCreateWorkoutFragmentToNewWorkoutListFragment action = BodyPartFragmentDirections.actionCreateWorkoutFragmentToNewWorkoutListFragment(workoutList);
        NavHostFragment.findNavController(this).navigate(action);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NewWorkoutViewModel.class);

        sharedWorkoutViewModel.getSelectedWorkouts().observe(getViewLifecycleOwner(), workouts -> {
            int workoutsLength = workouts.size();
            TextView tvBalloonAddedWorkouts = binding.tvBalloonAddedWorkouts;
            if (workoutsLength > 0) {
                tvBalloonAddedWorkouts.setVisibility(View.VISIBLE);
                tvBalloonAddedWorkouts.setText(String.format(Locale.US, "You have added %d workouts", workoutsLength));
                Animation slideUpAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_up);
                tvBalloonAddedWorkouts.startAnimation(slideUpAnimation);
            } else {
                tvBalloonAddedWorkouts.setVisibility(View.INVISIBLE);
            }
        });

        fetchBodyParts();
    }


    private void fetchBodyParts() {
        mViewModel.retrieveAllBodyPartsAndWorkouts().observe(getViewLifecycleOwner(), bodyPartWithWorkouts -> {
            List<BodyPartModel> tempBodyPartModelList = new ArrayList<>();
            for (BodyPartWithWorkouts bodyPartWithWorkout : bodyPartWithWorkouts) {
                String bucketName = "geargym";
                String objectKey = "bodypart/" + bodyPartWithWorkout.bodyPart.ImageUri;
                awss3Service.getObjectUrl(bucketName, objectKey, new AWSS3Service.UrlCallback() {

                    @Override
                    public void onUrlReady(String imageUrl) {
                        if (isAdded()) {
                            requireActivity().runOnUiThread(() -> {
                                String bodyPartName = bodyPartWithWorkout.bodyPart.Name;
                                int workoutsLength = bodyPartWithWorkout.workouts.size();
                                List<WorkoutModel> workouts = convertToWorkoutModels(bodyPartWithWorkout.workouts);
                                BodyPartModel bodyPartModel = new BodyPartModel(bodyPartName, imageUrl, workoutsLength, workouts);
                                tempBodyPartModelList.add(bodyPartModel);
                                if (tempBodyPartModelList.size() == bodyPartWithWorkouts.size()) {
                                    bodyPartModelList.clear();
                                    bodyPartModelList.addAll(tempBodyPartModelList);
                                    adapter.notifyDataSetChanged();
                                }
                            });
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.d("Error", "Error fetching image URL: " + e.getMessage());
                    }
                });
            }
        });
    }

    private List<WorkoutModel> convertToWorkoutModels(List<Workout> workouts) {
        List<WorkoutModel> workoutModels = new ArrayList<>();
        for (Workout workout : workouts) {
            String title = workout.Name;
            String duration = workout.Duration;
            String imageUrl = workout.ImageUri;
            workoutModels.add(new WorkoutModel(title, duration, imageUrl));
        }
        return workoutModels;
    }


    @Override
    public void onResume() {
        super.onResume();
        showToolBar();
        hideBottomAppBar();
        hideFab();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}