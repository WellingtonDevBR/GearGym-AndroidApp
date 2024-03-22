package com.gamezzar.geargymtest.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.gamezzar.geargymtest.databinding.WorkoutOptionCardBinding;
import com.gamezzar.geargymtest.domain.WorkoutModel;

import java.util.List;
import java.util.Locale;


public class WorkoutListOptionAdapter extends RecyclerView.Adapter<WorkoutListOptionAdapter.WorkoutChoiceListViewHolder> {
    private final List<WorkoutModel> workoutChoiceList;

    public WorkoutListOptionAdapter(List<WorkoutModel> workoutList) {
        this.workoutChoiceList = workoutList;
    }

    @NonNull
    @Override
    public WorkoutListOptionAdapter.WorkoutChoiceListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        WorkoutOptionCardBinding itemBinding = WorkoutOptionCardBinding.inflate(layoutInflater, parent, false);
        return new WorkoutListOptionAdapter.WorkoutChoiceListViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutListOptionAdapter.WorkoutChoiceListViewHolder holder, int position) {
        WorkoutModel workout = workoutChoiceList.get(position);
        holder.bind(workout);
    }

    @Override
    public int getItemCount() {
        return workoutChoiceList.size();
    }

    static class WorkoutChoiceListViewHolder extends RecyclerView.ViewHolder {
        private final WorkoutOptionCardBinding binding;

        public WorkoutChoiceListViewHolder(WorkoutOptionCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(WorkoutModel workout) {
            Glide.with(binding.ivWorkoutImage.getContext())
                    .load(workout.getImageUrl())
                    .transform(new CenterCrop(), new RoundedCorners(12)) // Use appropriate radius
                    .into(binding.ivWorkoutImage);
            binding.tvWorkoutName.setText(workout.getTitle());
            binding.tvWorkoutTime.setText(String.format(Locale.US, " " + "-" + " " + workout.getWorkoutDuration()));
            binding.checkBox.setActivated(true);
        }
    }
}
