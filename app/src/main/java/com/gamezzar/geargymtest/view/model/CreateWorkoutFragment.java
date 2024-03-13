package com.gamezzar.geargymtest.view.model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gamezzar.geargymtest.R;
import com.gamezzar.geargymtest.databinding.CreateWorkoutFragmentBinding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CreateWorkoutFragment extends Fragment {

    private CreateWorkoutViewModel mViewModel;
    private WorkoutAdapter adapter;
    private List<Workout> workoutList;
    private CreateWorkoutFragmentBinding binding;
    public static CreateWorkoutFragment newInstance() {
        return new CreateWorkoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Initialize the binding variable here correctly without re-declaring it
        binding = CreateWorkoutFragmentBinding.inflate(inflater, container, false);

        // Initialize your workout list here before setting the adapter
        workoutList = new ArrayList<>(); // This should be populated with actual data
        workoutList.add(new Workout("Chest", "Body Workout", R.drawable.chest_workout, 40));
        workoutList.add(new Workout("Shoulder", "Shoulder Workout", R.drawable.shoulder_workout, 40));
        workoutList.add(new Workout("ABS", "ABS Workout", R.drawable.abs_wokrout, 40));
        adapter = new WorkoutAdapter(workoutList);

        // You don't need to set up RecyclerView here anymore, move this to onViewCreated
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
        // Hide the Toolbar when the fragment becomes active
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            // Also hide the BottomNavigationView if needed
            BottomAppBar bottomAppBar = activity.findViewById(R.id.bottom_app_bar);
            FloatingActionButton floatingActionButton = activity.findViewById(R.id.fab);
            if (bottomAppBar != null) {
                bottomAppBar.setVisibility(View.GONE);
                floatingActionButton.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Set binding to null, since the view is about to be destroyed
        binding = null;
    }
}