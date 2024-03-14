package com.gamezzar.geargymtest.feature.workout.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gamezzar.geargymtest.core.Workout;
import com.gamezzar.geargymtest.databinding.ItemWorkoutTypeBinding;

import java.util.List;

public class WorkoutListAdapter extends RecyclerView.Adapter<WorkoutListAdapter.WorkoutListViewHolder> {

    private final List<Workout> workoutList;

    public WorkoutListAdapter(List<Workout> workoutList) {
        this.workoutList = workoutList;
    }

    @NonNull
    @Override
    public WorkoutListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemWorkoutTypeBinding binding = ItemWorkoutTypeBinding.inflate(layoutInflater, parent, false);
        return new WorkoutListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutListViewHolder holder, int position) {
        Workout workout = workoutList.get(position);
        //holder.binding.workoutTypeName.setText(workout.getName()); // Adjust this line according to your actual data and layout
    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }

    public static class WorkoutListViewHolder extends RecyclerView.ViewHolder {
        final ItemWorkoutTypeBinding binding;

        public WorkoutListViewHolder(ItemWorkoutTypeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
