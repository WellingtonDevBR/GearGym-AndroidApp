package com.gamezzar.geargymtest.core;

import android.content.Context;
import android.util.Log;

import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

public class CameraManager {
    private final Context context;
    private final LifecycleOwner lifecycleOwner;
    private final PreviewView previewView;
    private final ImageAnalysis.Analyzer analyzer;

    public CameraManager(Context context, LifecycleOwner lifecycleOwner, PreviewView previewView, ImageAnalysis.Analyzer analyzer) {
        this.context = context;
        this.lifecycleOwner = lifecycleOwner;
        this.previewView = previewView;
        this.analyzer = analyzer;
    }

    public void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(context);
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindCameraUseCases(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                Log.e("CameraManager", "Camera initialization failed", e);
            }
        }, ContextCompat.getMainExecutor(context));
    }

    private void bindCameraUseCases(ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder().build();
        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder().build();
        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(context), analyzer);

        CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;

        cameraProvider.unbindAll();
        cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, preview, imageAnalysis);
    }
}
