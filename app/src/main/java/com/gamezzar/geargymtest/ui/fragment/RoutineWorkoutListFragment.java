package com.gamezzar.geargymtest.ui.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gamezzar.geargymtest.R;
import com.gamezzar.geargymtest.databinding.RoutineWorkoutListFragmentBinding;
import com.gamezzar.geargymtest.domain.RoutineModel;
import com.gamezzar.geargymtest.ui.adapter.RoutineWorkoutListAdapter;
import com.gamezzar.geargymtest.viewmodel.RoutineWorkoutListViewModel;

public class RoutineWorkoutListFragment extends Fragment {

    private RoutineWorkoutListViewModel mViewModel;
    private RoutineWorkoutListFragmentBinding binding;
    private RoutineWorkoutListAdapter adapter;

    public static RoutineWorkoutListFragment newInstance() {
        return new RoutineWorkoutListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = RoutineWorkoutListFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RoutineWorkoutListViewModel.class);

        RoutineModel selectedRoutine = null;
        if (getArguments() != null) {
            selectedRoutine = getArguments().getParcelable("selectedRoutine", RoutineModel.class);
            if (selectedRoutine != null) {
                adapter = new RoutineWorkoutListAdapter(selectedRoutine.getRoutineWorkouts());
                binding.routineWorkoutsListRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.routineWorkoutsListRecycleView.setAdapter(adapter);
            } else {
                Log.e("RoutineWorkoutListFragment", "Selected routine is null.");
            }
        }
    }


}