package com.gamezzar.geargymtest.feature.workout;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WorkoutListAdapter extends RecyclerView.Adapter<WorkoutChoiceListAdapter.WorkoutChoiceListViewHolder> {

    private final List<Workout> workoutList;
    private final View.OnClickListener onItemClickListener;

    public WorkoutListAdapter(List<Workout> workoutList, View.OnClickListener onItemClickListener) {
        this.workoutList = workoutList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public WorkoutChoiceListAdapter.WorkoutChoiceListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutChoiceListAdapter.WorkoutChoiceListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
