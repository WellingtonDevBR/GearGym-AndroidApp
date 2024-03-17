package com.gamezzar.geargymtest.seedwork.utility;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import androidx.camera.core.ImageProxy;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class YUVToRGB {
    public Bitmap yuvToBitmap(ImageProxy image) {
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
        // Y plane is already in place
        yBuffer.get(nv21, 0, ySize);

        int yPos = yRowStride * image.getHeight();
        for (int i = 0; i < vBuffer.remaining(); i += uvPixelStride) {
            // U and V are swapped
            nv21[yPos++] = vBuffer.get(i);
            nv21[yPos++] = uBuffer.get(i);
        }

        YuvImage yuvImage = new YuvImage(nv21, ImageFormat.NV21, image.getWidth(), image.getHeight(), null);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, image.getWidth(), image.getHeight()), 100, out);

        byte[] imageBytes = out.toByteArray();
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
    }

}
