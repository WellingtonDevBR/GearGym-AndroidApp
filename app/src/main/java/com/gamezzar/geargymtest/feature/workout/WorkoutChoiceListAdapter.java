package com.gamezzar.geargymtest.feature.workout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gamezzar.geargymtest.databinding.ItemWorkoutListBinding;

import java.util.List;


public class WorkoutChoiceListAdapter extends RecyclerView.Adapter<WorkoutChoiceListAdapter.WorkoutChoiceListViewHolder> {
    private final List<Workout> workoutChoiceList;
    private final View.OnClickListener onItemClickListener;

    public WorkoutChoiceListAdapter(List<Workout> workoutList, View.OnClickListener onItemClickListener) {
        this.workoutChoiceList = workoutList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public WorkoutChoiceListAdapter.WorkoutChoiceListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemWorkoutListBinding itemBinding = ItemWorkoutListBinding.inflate(layoutInflater, parent, false);
        return new WorkoutChoiceListAdapter.WorkoutChoiceListViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutChoiceListAdapter.WorkoutChoiceListViewHolder holder, int position) {
        Workout workout = workoutChoiceList.get(position);
        holder.bind(workout, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return workoutChoiceList.size();
    }

    static class WorkoutChoiceListViewHolder extends RecyclerView.ViewHolder {
        private final ItemWorkoutListBinding binding;

        public WorkoutChoiceListViewHolder(ItemWorkoutListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Workout workout, View.OnClickListener clickListener) {
            // Bind the workout data to the views
            binding.ivWorkoutImage.setImageResource(workout.getImageResourceId());
            binding.tvWorkoutName.setText(workout.getSubtitle());
            binding.tvWorkoutTime.setText(workout.getFormattedWorkoutCount());
            binding.checkBox.setActivated(true);
            itemView.setTag(workout);
            itemView.setOnClickListener(clickListener);
        }
    }

}
