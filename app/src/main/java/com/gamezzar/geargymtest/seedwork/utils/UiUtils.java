package com.gamezzar.geargymtest.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.Button;

import androidx.core.content.ContextCompat;

public class UiUtils {

    public static void updateButtonWithCounter(Context context, Button button, int counter, int drawableResId) {
        Resources resources = context.getResources();
        float scale = resources.getDisplayMetrics().density;
        int width = (int) (24 * scale + 0.5f);
        int height = (int) (24 * scale + 0.5f);

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Drawable drawable = ContextCompat.getDrawable(context, drawableResId);
        if (drawable != null) {
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        }

        String counterText = String.valueOf(counter);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(16 * scale);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        int xPos = (canvas.getWidth() / 2);
        int yPos = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2));
        canvas.drawText(counterText, xPos, yPos, paint);

        Drawable finalDrawable = new BitmapDrawable(resources, bitmap);

        Drawable[] drawables = button.getCompoundDrawables();
        button.setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], finalDrawable, drawables[3]);
    }
}
