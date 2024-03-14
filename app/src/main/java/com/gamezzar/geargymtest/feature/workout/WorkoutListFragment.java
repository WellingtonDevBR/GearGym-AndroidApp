package com.gamezzar.geargymtest.feature.workout;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gamezzar.geargymtest.R;
import com.gamezzar.geargymtest.databinding.WorkoutListFragmentBinding;

public class WorkoutListFragment extends Fragment {

    private WorkoutListViewModel mViewModel;
    private WorkoutListFragmentBinding binding;

    public static WorkoutListFragment newInstance() {
        return new WorkoutListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = WorkoutListFragmentBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}