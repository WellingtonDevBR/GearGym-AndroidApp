package com.gamezzar.geargymtest.seedwork.ui;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import com.gamezzar.geargymtest.R;
import com.gamezzar.geargymtest.domain.SetModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.Objects;

public class CustomDialogFragment extends DialogFragment {
    public interface OnSetAddedListener {
        void onSetAdded(SetModel newSet);
    }

    private OnSetAddedListener mListener;

    public void setOnSetAddedListener(OnSetAddedListener listener) {
        mListener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(requireContext());
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_set, null);
        dialog.setContentView(dialogView);

        final TextInputEditText etWeight = dialogView.findViewById(R.id.et_weight);
        final TextInputEditText etRepetition = dialogView.findViewById(R.id.et_repetitions);

        Button btnConfirm = dialogView.findViewById(R.id.btnConfirm);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);

        btnConfirm.setOnClickListener(view -> {
            String weight = Objects.requireNonNull(etWeight.getText()).toString().trim();
            String repetitions = Objects.requireNonNull(etRepetition.getText()).toString().trim();
            if (!weight.isEmpty() && !repetitions.isEmpty()) {
                SetModel newSet = new SetModel("2", weight, repetitions);
                if (mListener != null) {
                    mListener.onSetAdded(newSet);
                }
                dismiss();
            } else {
                Toast.makeText(getContext(), "Please enter both weight and repetitions", Toast.LENGTH_LONG).show();
            }
        });
        btnCancel.setOnClickListener(view -> dismiss());
        return dialog;
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            requireActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            int dialogWidth = (int) (width * 0.9);
            dialog.getWindow().setLayout(dialogWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}
