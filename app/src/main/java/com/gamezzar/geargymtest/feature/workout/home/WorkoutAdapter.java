package com.gamezzar.geargymtest.feature.workout.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gamezzar.geargymtest.core.Workout;
import com.gamezzar.geargymtest.databinding.ItemWorkoutBinding;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder> {

    private List<Workout> workoutList;
    private View.OnClickListener onItemClickListener;

    public WorkoutAdapter(List<Workout> workoutList, View.OnClickListener onItemClickListener) {
        this.workoutList = workoutList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemWorkoutBinding itemBinding = ItemWorkoutBinding.inflate(layoutInflater, parent, false);
        return new WorkoutViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutViewHolder holder, int position) {
        Workout workout = workoutList.get(position);
        holder.bind(workout, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }

    static class WorkoutViewHolder extends RecyclerView.ViewHolder {
        private ItemWorkoutBinding binding;

        public WorkoutViewHolder(ItemWorkoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Workout workout, View.OnClickListener clickListener) {
            // Bind the workout data to the views
            binding.textWorkoutTitle.setText(workout.getTitle());
            binding.textWorkoutSubtitle.setText(workout.getSubtitle());
            binding.textWorkoutCount.setText(workout.getFormattedWorkoutCount());
            binding.imageWorkout.setImageResource(workout.getImageResourceId());
            itemView.setTag(workout); // or workout.getId() if you have IDs
            itemView.setOnClickListener(clickListener);
        }
    }
}
