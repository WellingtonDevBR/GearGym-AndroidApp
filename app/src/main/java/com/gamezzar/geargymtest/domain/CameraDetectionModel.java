package com.gamezzar.geargymtest.domain;

import android.graphics.Bitmap;
import android.view.View;

public class CameraDetectionModel {
    private final Bitmap image;
    private final String label;
    private final Float confidence;
    private final View.OnClickListener onClickListener;
    public CameraDetectionModel(Bitmap image, String label, Float confidence, View.OnClickListener onClickListener) {
        this.image = image;
        this.label = label;
        this.confidence = confidence;
        this.onClickListener = onClickListener;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getLabel() {
        return label;
    }

    public Float getConfidence() { return confidence; }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }
}
