package com.gamezzar.geargymtest.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.gamezzar.geargymtest.R;
import com.gamezzar.geargymtest.databinding.RoutineWorkoutListItemCardBinding;
import com.gamezzar.geargymtest.domain.WorkoutModel;

import java.util.List;

public class RoutineWorkoutListAdapter extends RecyclerView.Adapter<RoutineWorkoutListAdapter.RoutineWorkoutListViewHolder> {

    private List<WorkoutModel> workouts;

    public RoutineWorkoutListAdapter(List<WorkoutModel> workouts) {
        this.workouts = workouts;
    }

    @NonNull
    @Override
    public RoutineWorkoutListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Ensure the layout file name matches the expected one and is correctly inflated
        RoutineWorkoutListItemCardBinding itemCardBinding = RoutineWorkoutListItemCardBinding.inflate(layoutInflater, parent, false);
        return new RoutineWorkoutListViewHolder(itemCardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RoutineWorkoutListAdapter.RoutineWorkoutListViewHolder holder, int position) {
        WorkoutModel workoutModel = workouts.get(position);
        holder.bind(workoutModel);
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    static class RoutineWorkoutListViewHolder extends RecyclerView.ViewHolder {
        private RoutineWorkoutListItemCardBinding binding;
        public RoutineWorkoutListViewHolder(@NonNull RoutineWorkoutListItemCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding; // Make sure this line correctly assigns the passed binding object
        }

        public void bind(WorkoutModel workout) {
            System.out.println("testando" + workout.getImageName());
            Glide.with(binding.ivRoutineWorkoutItem.getContext())
                    .load(workout.getImageUrl())
                    .transform(new CenterCrop(), new RoundedCorners(18))
                    .into(binding.ivRoutineWorkoutItem);
            this.binding.tvWorkoukRoutineItemName.setText(workout.getTitle());
        }
    }
}
