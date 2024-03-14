package com.gamezzar.geargymtest.feature.workout.create;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gamezzar.geargymtest.R;
import com.gamezzar.geargymtest.databinding.NewWorkoutFragmentBinding;
import com.gamezzar.geargymtest.core.Workout;
import com.gamezzar.geargymtest.feature.workout.home.WorkoutAdapter;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NewWorkoutFragment extends Fragment {

    private NewWorkoutViewModel mViewModel;
    private WorkoutAdapter adapter;
    private List<Workout> workoutList;
    private NewWorkoutFragmentBinding binding;

    public static NewWorkoutFragment newInstance() {
        return new NewWorkoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = NewWorkoutFragmentBinding.inflate(inflater, container, false);

        workoutList = new ArrayList<>();
        // Add workout items to the list
        workoutList.add(new Workout("Chest", "Body Workout", R.drawable.chest_workout, 40));
        workoutList.add(new Workout("Shoulder", "Body Workout", R.drawable.shoulder_workout, 10));
        workoutList.add(new Workout("ABS", "Body Workout", R.drawable.abs_wokrout, 6));

        View.OnClickListener onItemClickListener = view -> {
            // Assuming each item has a tag set with its position or ID
            Workout selectedWorkout = (Workout) view.getTag();
            // Now you can navigate to the next fragment using the selected workout's details
            NavDirections action = NewWorkoutFragmentDirections.actionCreateWorkoutFragmentToWorkoutChoiceListFragment();

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