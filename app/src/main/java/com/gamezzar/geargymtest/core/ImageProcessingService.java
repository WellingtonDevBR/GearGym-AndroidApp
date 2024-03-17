package com.gamezzar.geargymtest.core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;

import androidx.camera.core.ImageProxy;

import com.amazonaws.services.rekognition.model.BoundingBox;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class ImageProcessingService {

    public ImageProcessingService() {
    }

    public Bitmap rotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public Bitmap cropDetectedObject(Bitmap originalBitmap, BoundingBox boundingBox) {
        int left = (int) (boundingBox.getLeft() * originalBitmap.getWidth());
        int top = (int) (boundingBox.getTop() * originalBitmap.getHeight());
        int width = (int) (boundingBox.getWidth() * originalBitmap.getWidth());
        int height = (int) (boundingBox.getHeight() * originalBitmap.getHeight());
        Bitmap bitmap = Bitmap.createBitmap(originalBitmap, left, top, width, height);
        int thumbnailWidth = 300;
        int thumbnailHeight = 500;
        return Bitmap.createScaledBitmap(bitmap, thumbnailWidth, thumbnailHeight, false);
    }

    public Bitmap convertYUTToRGB(ImageProxy image) {
        ImageProxy.PlaneProxy[] planes = image.getPlanes();
        ByteBuffer yBuffer = planes[0].getBuffer();
        ByteBuffer uBuffer = planes[1].getBuffer();
        ByteBuffer vBuffer = planes[2].getBuffer();

        int ySize = yBuffer.remaining();
        int uSize = uBuffer.remaining();
        int vSize = vBuffer.remaining();

        int yRowStride = planes[0].getRowStride();
        int uvPixelStride = planes[1].getPixelStride();
        int uvRowStride = planes[1].getRowStride();

        byte[] nv21 = new byte[ySize + uSize + vSize];
        yBuffer.get(nv21, 0, ySize);

        int yPos = yRowStride * image.getHeight();
        for (int i = 0; i < vBuffer.remaining(); i += uvPixelStride) {
            nv21[yPos++] = vBuffer.get(i);
            nv21[yPos++] = uBuffer.get(i);
        }

        YuvImage yuvImage = new YuvImage(nv21, ImageFormat.NV21, image.getWidth(), image.getHeight(), null);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, image.getWidth(), image.getHeight()), 100, out);

        byte[] imageBytes = out.toByteArray();
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
    }

    public byte[] bitmapToJpegByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

}
