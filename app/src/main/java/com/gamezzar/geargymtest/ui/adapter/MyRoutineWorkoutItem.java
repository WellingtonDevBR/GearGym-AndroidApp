package com.gamezzar.geargymtest.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gamezzar.geargymtest.databinding.MyRoutineWorkoutItemBinding;
import com.gamezzar.geargymtest.domain.RoutineModel;
import com.gamezzar.geargymtest.domain.SetModel;
import com.gamezzar.geargymtest.domain.WorkoutModel;

import java.util.List;
import java.util.Locale;

public class MyRoutineWorkoutItem extends RecyclerView.Adapter<MyRoutineWorkoutItem.MyRoutineWorkoutItemViewHolder> {

    private List<WorkoutModel> workouts;
    public MyRoutineWorkoutItem(List<WorkoutModel> workouts) {
        this.workouts = workouts;
    }

    @NonNull
    @Override
    public MyRoutineWorkoutItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MyRoutineWorkoutItemBinding binding = MyRoutineWorkoutItemBinding.inflate(layoutInflater);
        return new MyRoutineWorkoutItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRoutineWorkoutItemViewHolder holder, int position) {
        WorkoutModel workout = workouts.get(position);
        holder.bind(workout);
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    static class MyRoutineWorkoutItemViewHolder extends RecyclerView.ViewHolder {
        private MyRoutineWorkoutItemBinding binding;

        public MyRoutineWorkoutItemViewHolder(@NonNull MyRoutineWorkoutItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(WorkoutModel workout) {
            this.binding.tvWorkoutName.setText(workout.getTitle());
            int setCount = workout.getSets().size();
            this.binding.tvRepetition.setText(String.format(Locale.US, setCount + " Sets"));
        }
    }
}
