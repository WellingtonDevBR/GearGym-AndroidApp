package com.gamezzar.geargymtest.seedwork.service;

import android.graphics.Bitmap;

import com.gamezzar.geargymtest.domain.CameraDetectionModel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ObjectDetectionService {
    private final AWSRekognitionService awsRekognitionService;
    private final ImageProcessingService imageProcessingService;

    public ObjectDetectionService(AWSRekognitionService awsRekognitionService, ImageProcessingService imageProcessingService) {
        this.awsRekognitionService = awsRekognitionService;
        this.imageProcessingService = imageProcessingService;
    }

    public void detectObjects(Bitmap bitmap, Consumer<List<CameraDetectionModel>> onDetectionComplete) {
        byte[] jpegData = imageProcessingService.bitmapToJpegByteArray(bitmap);
        // Call AWS Rekognition to detect labels
        // This is a simplified version. You would need to call AWS Rekognition here and process the results.
        List<CameraDetectionModel> detectedObjects = new ArrayList<>();
        onDetectionComplete.accept(detectedObjects);
    }
}