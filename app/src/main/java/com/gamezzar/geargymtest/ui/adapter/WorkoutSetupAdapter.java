package com.gamezzar.geargymtest.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gamezzar.geargymtest.databinding.MyDayWorkoutCardBinding;
import com.gamezzar.geargymtest.domain.SetModel;
import com.gamezzar.geargymtest.domain.WorkoutModel;
import com.gamezzar.geargymtest.seedwork.ui.CustomDialogFragment;

import java.util.List;

public class WorkoutSetupAdapter extends RecyclerView.Adapter<WorkoutSetupAdapter.WorkoutSetupViewHolder> {

    private List<WorkoutModel> workouts;
    private final FragmentManager fragmentManager;
    private static OnWorkoutUpdatedListener workoutUpdatedListener;

    public WorkoutSetupAdapter(List<WorkoutModel> workouts, FragmentManager fragmentManager) {
        this.workouts = workouts;
        this.fragmentManager = fragmentManager;
    }

    public void setOnWorkoutUpdatedListener(OnWorkoutUpdatedListener listener) {
        workoutUpdatedListener = listener;
    }

    @NonNull
    @Override
    public WorkoutSetupAdapter.WorkoutSetupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyDayWorkoutCardBinding binding = MyDayWorkoutCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new WorkoutSetupViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutSetupViewHolder holder, int position) {
        WorkoutModel workout = workouts.get(position);
        holder.bind(workout, fragmentManager);

    }

    public void setWorkouts(List<WorkoutModel> workouts) {
        this.workouts = workouts;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }


    static class WorkoutSetupViewHolder extends RecyclerView.ViewHolder {
        final MyDayWorkoutCardBinding binding;

        public WorkoutSetupViewHolder(MyDayWorkoutCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final WorkoutModel workout, final FragmentManager fragmentManager) {
            this.binding.tvWorkoutName.setText(workout.getTitle());
            Glide.with(binding.ivWorkoutType.getContext()).load(workout.getImageUrl()).fitCenter().into(this.binding.ivWorkoutType);
            WorkoutSetupNestedAdapter nestedAdapter = new WorkoutSetupNestedAdapter(workout.getSets(), (set, nestedItemPosition, workoutModel) -> {
                workout.deleteSetAtPosition(nestedItemPosition);
                if (workoutUpdatedListener != null) {
                    workoutUpdatedListener.onWorkoutUpdated(workoutModel);
                }
                toggleEmptyStateView(workout.getSets().isEmpty());
            }, workout);

            binding.rvWorkoutItems.setLayoutManager(new LinearLayoutManager(binding.rvWorkoutItems.getContext()));
            binding.rvWorkoutItems.setAdapter(nestedAdapter);
            toggleEmptyStateView(workout.getSets().isEmpty());
            binding.btnAddSet.setOnClickListener(v -> {
                CustomDialogFragment dialog = new CustomDialogFragment();
                dialog.setOnSetAddedListener(newSet -> {
                    workout.addSet(newSet);
                    nestedAdapter.addSet(newSet);
                    if (workoutUpdatedListener != null) {
                        workoutUpdatedListener.onWorkoutUpdated(workout);
                    }
                    toggleEmptyStateView(false);
                });
                dialog.show(fragmentManager, "CustomDialogFragment");
            });
            itemView.setTag(workout);
        }
        private void toggleEmptyStateView(boolean isEmpty) {
            if (isEmpty) {
                binding.rvWorkoutItems.setVisibility(View.GONE);
                binding.textViewEmptyState.setVisibility(View.VISIBLE);
            } else {
                binding.rvWorkoutItems.setVisibility(View.VISIBLE);
                binding.textViewEmptyState.setVisibility(View.GONE);
            }
        }
    }
}