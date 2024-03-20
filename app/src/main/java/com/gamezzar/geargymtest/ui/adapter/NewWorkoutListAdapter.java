package com.gamezzar.geargymtest.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gamezzar.geargymtest.core.Workout;
import com.gamezzar.geargymtest.databinding.WorkoutSelectionItemCardBinding;

import java.util.List;


public class NewWorkoutListAdapter extends RecyclerView.Adapter<NewWorkoutListAdapter.WorkoutChoiceListViewHolder> {
    private final List<Workout> workoutChoiceList;

    public NewWorkoutListAdapter(List<Workout> workoutList) {
        this.workoutChoiceList = workoutList;
    }

    @NonNull
    @Override
    public NewWorkoutListAdapter.WorkoutChoiceListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        WorkoutSelectionItemCardBinding itemBinding = WorkoutSelectionItemCardBinding.inflate(layoutInflater, parent, false);
        return new NewWorkoutListAdapter.WorkoutChoiceListViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewWorkoutListAdapter.WorkoutChoiceListViewHolder holder, int position) {
        Workout workout = workoutChoiceList.get(position);
        holder.bind(workout);
    }

    @Override
    public int getItemCount() {
        return workoutChoiceList.size();
    }

    static class WorkoutChoiceListViewHolder extends RecyclerView.ViewHolder {
        private final WorkoutSelectionItemCardBinding binding;

        public WorkoutChoiceListViewHolder(WorkoutSelectionItemCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Workout workout) {
            // Bind the workout data to the views
            //binding.ivWorkoutImage.setImageResource(workout.getImageResourceId());
            binding.tvWorkoutName.setText(workout.getSubtitle());
            binding.tvWorkoutTime.setText(workout.getFormattedWorkoutCount());
            binding.checkBox.setActivated(true);
        }
    }
}
