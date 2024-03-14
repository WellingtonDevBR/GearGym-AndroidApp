package com.gamezzar.geargymtest.feature.workout.create;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gamezzar.geargymtest.R;
import com.gamezzar.geargymtest.core.Workout;
import com.gamezzar.geargymtest.databinding.NewWorkoutListFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class NewWorkoutListFragment extends Fragment {

    private NewWorkoutListViewModel mViewModel;
    NewWorkoutListAdapter adapter;
    private List<Workout> workoutChoiceList;
    private NewWorkoutListFragmentBinding binding;

    public static NewWorkoutListFragment newInstance() {
        return new NewWorkoutListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = NewWorkoutListFragmentBinding.inflate(inflater, container, false);
        workoutChoiceList = new ArrayList<>();
        workoutChoiceList.add(new Workout("Chest", "Body Workout", R.drawable.chest_workout, 40));

        adapter = new NewWorkoutListAdapter(workoutChoiceList);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}