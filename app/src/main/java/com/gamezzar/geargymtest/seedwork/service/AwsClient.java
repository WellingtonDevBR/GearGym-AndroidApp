package com.gamezzar.geargymtest.seedwork.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;

public abstract class AwsClient {

    protected BasicAWSCredentials awsClient;

    public AwsClient(String AccessKey, String SecretKey) {
        awsClient = new BasicAWSCredentials(AccessKey, SecretKey);

    }
}
