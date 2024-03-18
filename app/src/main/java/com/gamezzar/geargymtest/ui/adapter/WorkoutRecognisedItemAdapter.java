package com.gamezzar.geargymtest.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gamezzar.geargymtest.databinding.WorkoutRecognisedItemBinding;
import com.gamezzar.geargymtest.core.ObjectDetectionData;

import java.util.List;
import java.util.Locale;

public class WorkoutRecognisedItemAdapter extends RecyclerView.Adapter<WorkoutRecognisedItemAdapter.WorkoutRecognisedItemViewHolder> {
    private final List<ObjectDetectionData> equipmentLabels;
    private View.OnClickListener onItemClickListener;

    public WorkoutRecognisedItemAdapter(List<ObjectDetectionData> equipmentLabels) {
        this.equipmentLabels = equipmentLabels;
    }

    @NonNull
    @Override
    public WorkoutRecognisedItemAdapter.WorkoutRecognisedItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WorkoutRecognisedItemBinding binding = WorkoutRecognisedItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new WorkoutRecognisedItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutRecognisedItemViewHolder holder, int position) {
        ObjectDetectionData objectDetectionData = equipmentLabels.get(position);
        holder.bind(objectDetectionData, objectDetectionData.getOnClickListener());
    }

    @Override
    public int getItemCount() {
        return equipmentLabels.size();
    }

    public static class WorkoutRecognisedItemViewHolder extends RecyclerView.ViewHolder {

        final WorkoutRecognisedItemBinding binding;

        public WorkoutRecognisedItemViewHolder(@NonNull WorkoutRecognisedItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ObjectDetectionData objectDetectionData, View.OnClickListener clickListener) {
            String formattedConfidence = String.format(Locale.getDefault(), objectDetectionData.getLabel() + " " + "%.2f%%", objectDetectionData.getConfidence());
            this.binding.tvConfidenceLabel.setText(formattedConfidence);
            this.binding.ivRecognisedObject.setImageBitmap(objectDetectionData.getImage());
            itemView.setTag(objectDetectionData);
            itemView.setOnClickListener(clickListener);
        }
    }
}
