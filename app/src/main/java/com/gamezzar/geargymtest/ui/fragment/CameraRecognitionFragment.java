package com.gamezzar.geargymtest.ui.fragment;

import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.view.PreviewView;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.model.BoundingBox;
import com.amazonaws.services.rekognition.model.CustomLabel;
import com.amazonaws.services.rekognition.model.DetectCustomLabelsResult;
import com.gamezzar.geargymtest.BuildConfig;
import com.gamezzar.geargymtest.R;
import com.gamezzar.geargymtest.core.BaseFragment;
import com.gamezzar.geargymtest.seedwork.service.CameraManagerService;
import com.gamezzar.geargymtest.seedwork.service.ImageProcessingService;
import com.gamezzar.geargymtest.core.ObjectDetectionData;
import com.gamezzar.geargymtest.databinding.CameraRecognitionFragmentBinding;
import com.gamezzar.geargymtest.seedwork.service.AwsRekognitionService;
import com.gamezzar.geargymtest.ui.adapter.WorkoutRecognisedItemAdapter;
import com.gamezzar.geargymtest.viewmodel.CameraRecognitionViewModel;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CameraRecognitionFragment extends BaseFragment {

    private static AwsRekognitionService awsRekognitionService;
    CameraRecognitionFragmentBinding binding;
    private CameraRecognitionViewModel mViewModel;
    private WorkoutRecognisedItemAdapter adapter;
    private List<ObjectDetectionData> equipmentsRecognised;
    private ImageProcessingService imageProcessingService;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = CameraRecognitionFragmentBinding.inflate(inflater, container, false);
        imageProcessingService = new ImageProcessingService();
        equipmentsRecognised = new ArrayList<>();
        adapter = new WorkoutRecognisedItemAdapter(equipmentsRecognised);
        awsRekognitionService = new AwsRekognitionService(getContext(), BuildConfig.AWS_IDENTITY_POOL_ID, Regions.US_EAST_2);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CameraRecognitionViewModel.class);
        binding.ibCameraReturn.setOnClickListener(v -> navigateToHomeScreen());
        binding.rvRecognisedEquipments.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvRecognisedEquipments.setAdapter(adapter);
        PreviewView previewView = binding.viewFinder;
        ImageAnalysis.Analyzer analyzer = new MyImageAnalyzer();
        CameraManagerService cameraManagerService = new CameraManagerService(requireContext(), getViewLifecycleOwner(), previewView, analyzer);
        cameraManagerService.startCamera();
    }

    private class MyImageAnalyzer implements ImageAnalysis.Analyzer {
        private final ExecutorService executorService = Executors.newSingleThreadExecutor();
        private long lastAnalysisTime = 0;

        @Override
        public void analyze(@NonNull ImageProxy image) {
            long currentTime = System.currentTimeMillis();
            if (shouldAnalyzeFrame(currentTime)) {
                lastAnalysisTime = currentTime;
                processImageAsync(image);
            } else {
                image.close(); // closing the image to avoid some leak memory
            }
        }

        private boolean shouldAnalyzeFrame(long currentTime) {
            // milliseconds
            long analysisInterval = 5000;
            return currentTime - lastAnalysisTime >= analysisInterval;
        }

        private void processImageAsync(ImageProxy image) {
            executorService.execute(() -> {
                try {
                    Bitmap bitmap = processImageForAnalysis(image);
                    detectObjects(bitmap);
                } catch (Exception e) {
                    Log.e("MyImageAnalyzer", "Failed to analyze image", e);
                } finally {
                    image.close();
                }
            });
        }

        private Bitmap processImageForAnalysis(ImageProxy image) {
            Bitmap bitmap = imageProcessingService.convertYUTToRGB(image);
            return imageProcessingService.rotateBitmap(bitmap, 90);
        }

        private void detectObjects(Bitmap bitmap) {
            byte[] jpegData = imageProcessingService.bitmapToJpegByteArray(bitmap);
            DetectCustomLabelsResult result = awsRekognitionService.detectCustomLabels(ByteBuffer.wrap(jpegData));
            processDetectionResult(result, bitmap);
        }

        private void processDetectionResult(DetectCustomLabelsResult result, Bitmap bitmap) {
            requireActivity().runOnUiThread(() -> updateUIWithDetectionResult(result, bitmap));
        }

        private void updateUIWithDetectionResult(DetectCustomLabelsResult result, Bitmap bitmap) {
            List<CustomLabel> labels = result.getCustomLabels();
            if (labels.isEmpty()) return;

            for (CustomLabel label : labels) {
                processLabel(label, bitmap);
            }
        }

        private void processLabel(CustomLabel label, Bitmap bitmap) {

            if (label.getGeometry() != null && label.getGeometry().getBoundingBox() != null) {
                Log.d("boundingBoxes", "Bounding: " + label.getGeometry().getBoundingBox());
                BoundingBox boundingBox = label.getGeometry().getBoundingBox();
                // Now you have the bounding box and can proceed with cropping the bitmap
                Bitmap croppedBitmap = imageProcessingService.cropDetectedObject(bitmap, boundingBox);
                updateDetectedEquipments(croppedBitmap, label);
            }
        }

        private void updateDetectedEquipments(Bitmap croppedBitmap, CustomLabel label) {
            ObjectDetectionData data = new ObjectDetectionData(croppedBitmap, label.getName(), label.getConfidence());
            if (equipmentsRecognised.size() >= 3) {
                equipmentsRecognised.remove(0);
                adapter.notifyItemRemoved(0);
            }
            equipmentsRecognised.add(data);
            adapter.notifyItemInserted(equipmentsRecognised.size() - 1);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hideToolBar();
        hideBottomAppBar();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void navigateToHomeScreen() {
        NavHostFragment.findNavController(this).navigate(R.id.action_cameraRecognitionFragment_to_homeFragment);
    }
}
