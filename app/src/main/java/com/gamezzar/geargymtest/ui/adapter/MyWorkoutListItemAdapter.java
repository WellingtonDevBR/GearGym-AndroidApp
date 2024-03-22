package com.gamezzar.geargymtest.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gamezzar.geargymtest.databinding.MyWorkoutListItemBinding;

public class MyWorkoutListItemAdapter extends RecyclerView.Adapter<MyWorkoutListItemAdapter.WorkoutListItemViewHolder> {
    private final String workoutName;

    public MyWorkoutListItemAdapter(String workoutName) {
        this.workoutName = workoutName;
    }

    @NonNull
    @Override
    public WorkoutListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MyWorkoutListItemBinding binding = MyWorkoutListItemBinding.inflate(layoutInflater, parent, false);
        return new WorkoutListItemViewHolder(binding);
    }

    public static class WorkoutListItemViewHolder extends RecyclerView.ViewHolder {

        final MyWorkoutListItemBinding binding;

        public WorkoutListItemViewHolder(@NonNull MyWorkoutListItemBinding binding) {
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
