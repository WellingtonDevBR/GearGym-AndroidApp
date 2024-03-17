package com.gamezzar.geargymtest.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gamezzar.geargymtest.R;
import com.gamezzar.geargymtest.core.BaseFragment;
import com.gamezzar.geargymtest.databinding.NewWorkoutFragmentBinding;
import com.gamezzar.geargymtest.core.Workout;
import com.gamezzar.geargymtest.ui.adapter.WorkoutAdapter;
import com.gamezzar.geargymtest.viewmodel.NewWorkoutViewModel;

import java.util.ArrayList;
import java.util.List;

public class NewWorkoutFragment extends BaseFragment {

    private NewWorkoutViewModel mViewModel;
    private WorkoutAdapter adapter;
    private List<Workout> workoutList;
    private NewWorkoutFragmentBinding binding;

    public static NewWorkoutFragment newInstance() {
        return new NewWorkoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = NewWorkoutFragmentBinding.inflate(inflater, container, false);

        workoutList = new ArrayList<>();
        // Add workout items to the list
        workoutList.add(new Workout("Chest", "Body Workout", R.drawable.chest_workout, 40));
        workoutList.add(new Workout("Shoulder", "Body Workout", R.drawable.shoulder_workout, 10));
        workoutList.add(new Workout("ABS", "Body Workout", R.drawable.abs_wokrout, 6));

        View.OnClickListener onItemClickListener = view -> {
            Workout selectedWorkout = (Workout) view.getTag();
            NavDirections action = NewWorkoutFragmentDirections.actionCreateWorkoutFragmentToNewWorkoutListFragment();
            NavHostFragment.findNavController(NewWorkoutFragment.this).navigate(action);
        };

        adapter = new WorkoutAdapter(workoutList, onItemClickListener);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set up the RecyclerView
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
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