package com.gamezzar.geargymtest.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.gamezzar.geargymtest.databinding.WorkoutOptionCardBinding;
import com.gamezzar.geargymtest.domain.WorkoutModel;

import java.util.List;
import java.util.Locale;


public class WorkoutListOptionAdapter extends RecyclerView.Adapter<WorkoutListOptionAdapter.WorkoutListOptionViewHolder> {
    private final List<WorkoutModel> workoutChoiceList;
    private final OnWorkoutCheckedChangeListener checkedChangeListener;

    public WorkoutListOptionAdapter(List<WorkoutModel> workoutList, OnWorkoutCheckedChangeListener checkedChangeListener) {
        this.workoutChoiceList = workoutList;
        this.checkedChangeListener = checkedChangeListener;
    }

    public void updateList(List<WorkoutModel> newList) {
        workoutChoiceList.clear();
        workoutChoiceList.addAll(newList);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public WorkoutListOptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        WorkoutOptionCardBinding itemBinding = WorkoutOptionCardBinding.inflate(layoutInflater, parent, false);
        return new WorkoutListOptionViewHolder(itemBinding); // Pass the listener here
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutListOptionAdapter.WorkoutListOptionViewHolder holder, int position) {
        WorkoutModel workout = workoutChoiceList.get(position);
        holder.bind(workout);
    }

    @Override
    public int getItemCount() {
        return workoutChoiceList.size();
    }

    class WorkoutListOptionViewHolder extends RecyclerView.ViewHolder {
        private final WorkoutOptionCardBinding binding;

        public WorkoutListOptionViewHolder(WorkoutOptionCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final WorkoutModel workout) {
            Glide.with(binding.ivWorkoutImage.getContext())
                    .load(workout.getImageUrl())
                    .transform(new CenterCrop(), new RoundedCorners(18))
                    .into(binding.ivWorkoutImage);
            binding.tvWorkoutName.setText(workout.getTitle());
            binding.tvWorkoutTime.setText(String.format(Locale.US, " " + "-" + " " + workout.getWorkoutDuration()));
            binding.cbWorkout.setChecked(workout.getChecked());
//            binding.cbWorkout.setOnCheckedChangeListener((buttonView, isChecked) -> workout.setChecked(isChecked));
            binding.cbWorkout.setOnCheckedChangeListener((buttonView, isChecked) -> {
                workout.setChecked(isChecked);
                if (checkedChangeListener != null) {
                    checkedChangeListener.onCheckedChanged();
                }
            });
        }
    }
}
