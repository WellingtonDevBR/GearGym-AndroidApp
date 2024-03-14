package com.gamezzar.geargymtest.feature.workout.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gamezzar.geargymtest.core.Workout;
import com.gamezzar.geargymtest.databinding.WorkoutItemsInCardBinding;
import com.gamezzar.geargymtest.databinding.WorkoutListItemCardBinding;

import java.util.List;

public class WorkoutListItemAdapter extends RecyclerView.Adapter<WorkoutListItemAdapter.WorkoutListItemViewHolder> {
    private final String workoutName;

    public WorkoutListItemAdapter(String workoutName) {
        this.workoutName = workoutName;
    }

    @NonNull
    @Override
    public WorkoutListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        WorkoutItemsInCardBinding binding = WorkoutItemsInCardBinding.inflate(layoutInflater, parent, false);
        return new WorkoutListItemViewHolder(binding);
    }

    public static class WorkoutListItemViewHolder extends RecyclerView.ViewHolder {

        final WorkoutItemsInCardBinding binding;

        public WorkoutListItemViewHolder(@NonNull WorkoutItemsInCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutListItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
