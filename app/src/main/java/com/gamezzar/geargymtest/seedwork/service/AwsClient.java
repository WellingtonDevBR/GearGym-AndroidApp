package com.gamezzar.geargymtest.seedwork.service;

import android.content.Context;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognitionClient;

public abstract class AwsClient {
    protected CognitoCachingCredentialsProvider credentialsProvider;
    public AwsClient(Context context, String identityPoolId, Regions region) {
        credentialsProvider = new CognitoCachingCredentialsProvider(context, identityPoolId, region);
    }
}
