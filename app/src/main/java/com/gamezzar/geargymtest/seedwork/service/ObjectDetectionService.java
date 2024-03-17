package com.gamezzar.geargymtest.seedwork.service;

import android.graphics.Bitmap;

import com.gamezzar.geargymtest.core.ObjectDetectionData;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ObjectDetectionService {
    private final AwsRekognitionService awsRekognitionService;
    private final ImageProcessingService imageProcessingService;

    public ObjectDetectionService(AwsRekognitionService awsRekognitionService, ImageProcessingService imageProcessingService) {
        this.awsRekognitionService = awsRekognitionService;
        this.imageProcessingService = imageProcessingService;
    }

    public void detectObjects(Bitmap bitmap, Consumer<List<ObjectDetectionData>> onDetectionComplete) {
        byte[] jpegData = imageProcessingService.bitmapToJpegByteArray(bitmap);
        // Call AWS Rekognition to detect labels
        // This is a simplified version. You would need to call AWS Rekognition here and process the results.
        List<ObjectDetectionData> detectedObjects = new ArrayList<>();
        onDetectionComplete.accept(detectedObjects);
    }
}