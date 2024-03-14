package com.gamezzar.geargymtest.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gamezzar.geargymtest.core.Workout;
import com.gamezzar.geargymtest.databinding.WorkoutListItemCardBinding;

import java.util.List;

public class WorkoutListAdapter extends RecyclerView.Adapter<WorkoutListAdapter.WorkoutListViewHolder> {

    private final List<Workout> workoutList;

    public WorkoutListAdapter(List<Workout> workoutList) {
        this.workoutList = workoutList;
    }

    @NonNull
    @Override
    public WorkoutListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        WorkoutListItemCardBinding binding = WorkoutListItemCardBinding.inflate(layoutInflater, parent, false);
        return new WorkoutListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutListViewHolder holder, int position) {
        Workout workout = workoutList.get(position);
        holder.bind(workout);

        // Assuming each Workout has a method `getWorkoutChoices()` that returns List<Workout>
        String workoutChoices = workout.getTitle();
        WorkoutListItemAdapter nestedAdapter = new WorkoutListItemAdapter(workoutChoices);
        holder.nestedRecyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        holder.nestedRecyclerView.setAdapter(nestedAdapter);

        //holder.binding.workoutTypeName.setText(workout.getName()); // Adjust this line according to your actual data and layout
    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }

    public static class WorkoutListViewHolder extends RecyclerView.ViewHolder {
        private final WorkoutListItemCardBinding binding;
        final RecyclerView nestedRecyclerView;

        public WorkoutListViewHolder(WorkoutListItemCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.nestedRecyclerView = binding.rvWorkoutItems; // Ensure your WorkoutListItemCardBinding has a RecyclerView with this ID
        }

        public void bind(Workout workout) {
            // Here, bind the primary workout data to your views as needed
        }
    }
}
