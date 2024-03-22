package com.gamezzar.geargymtest.seedwork.service;

import android.content.Context;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.HttpMethod;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

import java.net.URL;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AWSS3Service extends AWSClient {

    private final AmazonS3Client s3Client;

    public AWSS3Service(Context context, String identityPoolId, Regions region) {
        super(context, identityPoolId, region);
        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setSignerOverride("AWSS3V4SignerType");
        s3Client = new AmazonS3Client(credentialsProvider, clientConfig);
        s3Client.setRegion(Region.getRegion(region));
    }

    public void getObjectUrl(String bucketName, String objectKey, UrlCallback callback) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                Date expiration = new Date();
                long expTimeMillis = System.currentTimeMillis();
                expTimeMillis += 1000 * 60 * 60; // 1 hour
                expiration.setTime(expTimeMillis);

                GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, objectKey).withMethod(HttpMethod.GET).withExpiration(expiration);

                URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
                callback.onUrlReady(url.toString());
            } catch (Exception e) {
                callback.onError(e);
            } finally {
                executor.shutdown();
            }
        });
    }

    public interface UrlCallback {
        void onUrlReady(String url);

        void onError(Exception e);
    }
}
