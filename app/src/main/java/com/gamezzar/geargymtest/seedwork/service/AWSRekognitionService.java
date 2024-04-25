package com.gamezzar.geargymtest.seedwork.service;

import android.content.Context;
import android.util.Log;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognitionClient;
import com.amazonaws.services.rekognition.model.DetectCustomLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectCustomLabelsResult;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.Image;
import com.gamezzar.geargymtest.BuildConfig;

import java.nio.ByteBuffer;

public class AWSRekognitionService extends AWSClient {

    protected AmazonRekognitionClient rekognitionClient;

    public AWSRekognitionService(Context context, String identityPoolId, Regions region) {
        super(context, identityPoolId, region);
        rekognitionClient = new AmazonRekognitionClient(credentialsProvider);
        rekognitionClient.setRegion(Region.getRegion(region));
    }

    public DetectCustomLabelsResult detectCustomLabels(ByteBuffer imageBytes) {
        // Log request details
        Log.d("Rekognition", "Image Size sent: " + imageBytes.remaining() + " bytes");

        String projectVersionArn = BuildConfig.AWS_REKOGNITION_VERSION_ARN;
        DetectCustomLabelsRequest request = new DetectCustomLabelsRequest()
                .withProjectVersionArn(projectVersionArn)
                .withImage(new Image().withBytes(imageBytes))
                .withMaxResults(10)
                .withMinConfidence(20F);
        // Log response details
        DetectCustomLabelsResult result = rekognitionClient.detectCustomLabels(request);
        Log.d("Rekognition", "Label detection response: " + result.toString());

        return rekognitionClient.detectCustomLabels(request);
    }
}
