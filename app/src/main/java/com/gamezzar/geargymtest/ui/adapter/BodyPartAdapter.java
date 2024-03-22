package com.gamezzar.geargymtest.ui.adapter;

import com.gamezzar.geargymtest.databinding.BodyPartCardBinding;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gamezzar.geargymtest.domain.BodyPartModel;

import java.util.List;
import java.util.Locale;

public class BodyPartAdapter extends RecyclerView.Adapter<BodyPartAdapter.BodyPartViewHolder> {

    private final List<BodyPartModel> bodyPartModelList;
    private final BodyPartClickListener bodyPartClickListener;

    public interface BodyPartClickListener {
        void onBodyPartClicked(BodyPartModel bodyPart);
    }

    public BodyPartAdapter(List<BodyPartModel> bodyPartModelList, BodyPartClickListener listener) {
        this.bodyPartModelList = bodyPartModelList;
        this.bodyPartClickListener = listener;
    }

    @NonNull
    @Override
    public BodyPartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        BodyPartCardBinding binding = BodyPartCardBinding.inflate(layoutInflater, parent, false);
        return new BodyPartViewHolder(binding, bodyPartClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BodyPartViewHolder holder, int position) {
        BodyPartModel bodyPart = bodyPartModelList.get(position);
        holder.bind(bodyPart);
    }

    @Override
    public int getItemCount() {
        return bodyPartModelList.size();
    }

    static class BodyPartViewHolder extends RecyclerView.ViewHolder {
        private final BodyPartCardBinding binding;

        public BodyPartViewHolder(BodyPartCardBinding binding, BodyPartClickListener clickListener) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(v -> clickListener.onBodyPartClicked((BodyPartModel) itemView.getTag()));
        }

        public void bind(BodyPartModel bodyPart) {
            String name = bodyPart.getName();
            if (!name.isEmpty()) {
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
            }
            binding.textWorkoutTitle.setText(name);
            binding.textWorkoutCount.setText(String.format(Locale.US, "%d", bodyPart.getWorkoutSize()));
            binding.textWorkoutSubtitle.setText(String.format(Locale.US, name + " " + "Workouts"));
            Glide.with(binding.ivWorkoutType.getContext()).load(bodyPart.getImageURL()).into(binding.ivWorkoutType);
            itemView.setTag(bodyPart);
        }
    }
}
