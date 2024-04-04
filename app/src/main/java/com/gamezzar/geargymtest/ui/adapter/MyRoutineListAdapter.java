package com.gamezzar.geargymtest.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gamezzar.geargymtest.databinding.MyRoutineWorkoutsCardBinding;
import com.gamezzar.geargymtest.domain.RoutineModel;

import java.util.List;
import java.util.Locale;

public class MyRoutineListAdapter extends RecyclerView.Adapter<MyRoutineListAdapter.RoutineViewHolder> {

    private static List<RoutineModel> routines;

    public MyRoutineListAdapter(List<RoutineModel> routines) {
        this.routines = routines;
    }

    @NonNull
    @Override
    public RoutineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MyRoutineWorkoutsCardBinding binding = MyRoutineWorkoutsCardBinding.inflate(layoutInflater, parent, false);
        return new RoutineViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RoutineViewHolder holder, int position) {
        RoutineModel routine = routines.get(position);
        holder.bind(routine);
    }

    public RoutineModel getItemAtPosition(int position) {
        return routines.get(position);
    }

    @Override
    public int getItemCount() {
        return routines.size();
    }

    public static class RoutineViewHolder extends RecyclerView.ViewHolder {
        private final MyRoutineWorkoutsCardBinding binding;

        public RoutineViewHolder(MyRoutineWorkoutsCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(RoutineModel routine) {
            MyRoutineWorkoutItem nestedAdapter = new MyRoutineWorkoutItem(routine.getRoutineWorkouts());
            binding.rvWorkoutItems.setLayoutManager(new LinearLayoutManager(binding.rvWorkoutItems.getContext(), LinearLayoutManager.VERTICAL, false));
            binding.rvWorkoutItems.setAdapter(nestedAdapter);

            binding.tvRoutineName.setText(String.format(Locale.US, routine.getTitle() + " " + "Workouts"));
            binding.tvWorkoutName.setText(String.format(Locale.US,"%d Workouts", routine.getRoutineWorkouts().size()));

//            binding.btnAddSet.setVisibility(View.INVISIBLE);
//            Glide.with(binding.ivWorkoutType.getContext())
//                    .load(routine.getRoutineWorkouts().get(0).getImageUrl())
//                    .transform(new CenterCrop(), new RoundedCorners(18))
//                    .into(binding.ivWorkoutType);
        }
    }
}
