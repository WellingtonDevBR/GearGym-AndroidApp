package com.gamezzar.geargymtest.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gamezzar.geargymtest.seedwork.shared.BaseFragment;
import com.gamezzar.geargymtest.domain.WorkoutModel;
import com.gamezzar.geargymtest.databinding.MyWorkoutListFragmentBinding;
import com.gamezzar.geargymtest.ui.adapter.MyWorkoutListAdapter;
import com.gamezzar.geargymtest.viewmodel.WorkoutListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyWorkoutListFragment extends BaseFragment {

    private WorkoutListViewModel mViewModel;
    private MyWorkoutListFragmentBinding binding;
    private MyWorkoutListAdapter adapter;
    private List<WorkoutModel> workoutList;

    public static MyWorkoutListFragment newInstance() {
        return new MyWorkoutListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = MyWorkoutListFragmentBinding.inflate(inflater, container, false);

        workoutList = new ArrayList<>();
        adapter = new MyWorkoutListAdapter(workoutList);
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