package com.gamezzar.geargymtest.seedwork.service;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClient;
import com.amazonaws.services.rekognition.model.BoundingBox;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.Label;

import java.nio.ByteBuffer;
import java.util.List;

public class AwsRekognitionService extends AwsClient {
    private final AmazonRekognition rekognitionClient;

    public AwsRekognitionService(String accessKey, String secretKey) {
        super(accessKey, secretKey);
        rekognitionClient = new AmazonRekognitionClient(awsClient);
        rekognitionClient.setRegion(Region.getRegion(Regions.US_EAST_2));
    }

    public DetectLabelsResult detectLabels(ByteBuffer imageBytes, int maxLabels, float minConfidence) {
        DetectLabelsRequest request = new DetectLabelsRequest().withImage(new Image().withBytes(imageBytes)).withMaxLabels(maxLabels).withMinConfidence(minConfidence);
        return rekognitionClient.detectLabels(request);
    }
}
