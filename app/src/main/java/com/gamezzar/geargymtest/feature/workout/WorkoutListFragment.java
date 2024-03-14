package com.gamezzar.geargymtest.feature.workout;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gamezzar.geargymtest.R;
import com.gamezzar.geargymtest.databinding.WorkoutListFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class WorkoutListFragment extends Fragment {

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

        View.OnClickListener onItemClickListener = view -> {
            // Assuming each item has a tag set with its position or ID
            Workout selectedWorkout = (Workout) view.getTag();
            // Now you can navigate to the next fragment using the selected workout's details
            NavDirections action = CreateWorkoutFragmentDirections.actionCreateWorkoutFragmentToWorkoutChoiceListFragment();

            NavHostFragment.findNavController(WorkoutListFragment.this).navigate(action);
        };

        adapter = new WorkoutListAdapter(workoutList, onItemClickListener);
        binding.recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recycleView.setAdapter(adapter);

        return binding.getRoot();
    }
}