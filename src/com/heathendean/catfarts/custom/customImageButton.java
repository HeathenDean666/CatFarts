package com.heathendean.catfarts.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;

public class customImageButton extends AppCompatImageButton {

    public customImageButton(@NonNull Context context) {
        super(context);
    }

    public customImageButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        super.onTouchEvent(event);

        Drawable buttonImage = this.getBackground();
        Bitmap bmp = drawableToBitmap(buttonImage);

        if (event.getAction() == MotionEvent.ACTION_DOWN){
			int color = 0;
			try {
				color = bmp.getPixel((int) event.getX(), (int) event.getY());
			} catch (Exception e) {
				e.printStackTrace();
			}
            return color != Color.TRANSPARENT;

        }
        return false;
    }

    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }
}
