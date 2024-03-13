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
import com.gamezzar.geargymtest.databinding.WorkoutChoiceListFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class WorkoutChoiceListFragment extends Fragment {

    private WorkoutChoiceListViewModel mViewModel;
    WorkoutChoiceListAdapter adapter;
    private List<Workout> workoutChoiceList;
    private WorkoutChoiceListFragmentBinding binding;

    public static WorkoutChoiceListFragment newInstance() {
        return new WorkoutChoiceListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = WorkoutChoiceListFragmentBinding.inflate(inflater, container, false);
        workoutChoiceList = new ArrayList<>();
        workoutChoiceList.add(new Workout("Chest", "Body Workout", R.drawable.chest_workout, 40));

        View.OnClickListener onItemClickListener = view -> {
            // Assuming each item has a tag set with its position or ID
            Workout selectedWorkout = (Workout) view.getTag();
            // Now you can navigate to the next fragment using the selected workout's details
            NavDirections action = CreateWorkoutFragmentDirections.actionCreateWorkoutFragmentToWorkoutChoiceListFragment();

            NavHostFragment.findNavController(WorkoutChoiceListFragment.this).navigate(action);
        };

        adapter = new WorkoutChoiceListAdapter(workoutChoiceList, onItemClickListener);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}