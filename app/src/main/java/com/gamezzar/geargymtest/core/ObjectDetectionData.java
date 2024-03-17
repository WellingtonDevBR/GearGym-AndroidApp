package com.gamezzar.geargymtest.core;

import android.graphics.Bitmap;

public class ObjectDetectionData {
    private final Bitmap image;
    private final String label;
    private final Float confidence;
    public ObjectDetectionData(Bitmap image, String label, Float confidence) {
        this.image = image;
        this.label = label;
        this.confidence = confidence;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getLabel() {
        return label;
    }

    public Float getConfidence() { return confidence; }

}
