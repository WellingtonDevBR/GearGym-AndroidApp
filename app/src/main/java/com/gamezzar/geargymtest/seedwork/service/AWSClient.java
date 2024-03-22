package com.gamezzar.geargymtest.seedwork.service;

import android.content.Context;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;

public abstract class AWSClient {
    protected CognitoCachingCredentialsProvider credentialsProvider;
    public AWSClient(Context context, String identityPoolId, Regions region) {
        credentialsProvider = new CognitoCachingCredentialsProvider(context, identityPoolId, region);
    }
}
