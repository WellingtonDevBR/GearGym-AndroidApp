package com.gamezzar.geargymtest.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gamezzar.geargymtest.databinding.MyRoutineWorkoutItemBinding;

public class MyRoutineWorkoutSetItem extends RecyclerView.Adapter<MyRoutineWorkoutSetItem.MyRoutineWOrkoutSetItemViewHolder> {
    private final String workoutName;

    public MyRoutineWorkoutSetItem(String workoutName) {
        this.workoutName = workoutName;
    }

    @NonNull
    @Override
    public MyRoutineWOrkoutSetItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MyRoutineWorkoutItemBinding binding = MyRoutineWorkoutItemBinding.inflate(layoutInflater, parent, false);
        return new MyRoutineWOrkoutSetItemViewHolder(binding);
    }

    public static class MyRoutineWOrkoutSetItemViewHolder extends RecyclerView.ViewHolder {

        final MyRoutineWorkoutItemBinding binding;

        public MyRoutineWOrkoutSetItemViewHolder(@NonNull MyRoutineWorkoutItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyRoutineWOrkoutSetItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
