package com.gamezzar.geargymtest.feature.workout.list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gamezzar.geargymtest.R;
import com.gamezzar.geargymtest.core.BaseFragment;
import com.gamezzar.geargymtest.core.Workout;
import com.gamezzar.geargymtest.databinding.WorkoutListFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class WorkoutListFragment extends BaseFragment {

    private WorkoutListViewModel mViewModel;
    private WorkoutListFragmentBinding binding;
    private WorkoutListAdapter adapter;
    private List<Workout> workoutList;

    public static WorkoutListFragment newInstance() {
        return new WorkoutListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = WorkoutListFragmentBinding.inflate(inflater, container, false);

        workoutList = new ArrayList<>();
        // Add workout items to the list
        workoutList.add(new Workout("Chest", "Body Workout", R.drawable.chest_workout, 40));
        workoutList.add(new Workout("Shoulder", "Body Workout", R.drawable.shoulder_workout, 10));
        workoutList.add(new Workout("ABS", "Body Workout", R.drawable.abs_wokrout, 6));
        workoutList.add(new Workout("ABS", "Body Workout", R.drawable.abs_wokrout, 6));
        workoutList.add(new Workout("ABS", "Body Workout", R.drawable.abs_wokrout, 6));

        adapter = new WorkoutListAdapter(workoutList);
        binding.recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recycleView.setAdapter(adapter);

        return binding.getRoot();
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