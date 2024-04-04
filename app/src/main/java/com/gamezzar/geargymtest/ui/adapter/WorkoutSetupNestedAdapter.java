package com.gamezzar.geargymtest.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gamezzar.geargymtest.databinding.MyRoutineWorkoutItemBinding;
import com.gamezzar.geargymtest.databinding.MyRoutineWorkoutSetItemBinding;
import com.gamezzar.geargymtest.domain.SetModel;
import com.gamezzar.geargymtest.domain.WorkoutModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WorkoutSetupNestedAdapter extends RecyclerView.Adapter<WorkoutSetupNestedAdapter.WorkoutSetupNestedViewHolder> {
    private final List<SetModel> sets;
    private final OnSetRemovedListener onSetRemovedListener;
    private WorkoutModel workout;

    public WorkoutSetupNestedAdapter(List<SetModel> sets, OnSetRemovedListener onSetRemovedListener, WorkoutModel workout) {
        this.sets = new ArrayList<>(sets);
        this.onSetRemovedListener = onSetRemovedListener;
        this.workout = workout;
    }

    @NonNull
    @Override
    public WorkoutSetupNestedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyRoutineWorkoutSetItemBinding binding = MyRoutineWorkoutSetItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new WorkoutSetupNestedViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutSetupNestedViewHolder holder, int position) {
        SetModel set = sets.get(position);
        holder.bind(set, position, this.onSetRemovedListener);
    }

    public void addSet(SetModel set) {
        sets.add(set);
        notifyItemInserted(sets.size() - 1);
    }

    @Override
    public int getItemCount() {
        return sets.size();
    }

    class WorkoutSetupNestedViewHolder extends RecyclerView.ViewHolder {
        final MyRoutineWorkoutSetItemBinding binding;

        public WorkoutSetupNestedViewHolder(@NonNull MyRoutineWorkoutSetItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final SetModel set, final int position, OnSetRemovedListener onSetRemovedListener) {
            this.binding.tvSetId.setText(String.format(Locale.US, "%s", set.getRow()));
            this.binding.tvKg.setText(String.format(Locale.US, "%s", set.getKg()));
            this.binding.tvRepetition.setText(String.format(Locale.US, "%s", set.getRepetitions()));
            this.binding.ibRemove.setOnClickListener(v -> {
                sets.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, getItemCount());
                if (onSetRemovedListener != null) {
                    onSetRemovedListener.onSetRemoved(set, position, workout);
                }
            });
            itemView.setTag(set);
        }
    }
}
