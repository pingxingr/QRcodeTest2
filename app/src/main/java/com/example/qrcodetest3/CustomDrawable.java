package com.example.qrcodetest3;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomDrawable extends Drawable {
    private Paint srcPaint;
    private Path srcPath = new Path();

    private Drawable innerDrawable;


    public CustomDrawable(Drawable innerDrawable) {
        this.innerDrawable = innerDrawable;
        srcPath.addRect(300 , 300, 300, 300, Path.Direction.CW);
        srcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        srcPaint.setColor(0x80000000);
    }

    public void setSrcPath(Path srcPath) {
        this.srcPath = srcPath;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        innerDrawable.setBounds(getBounds());
        if(srcPath == null || srcPath.isEmpty()) {
            innerDrawable.draw(canvas);
        } else {
            int saveCount = canvas.saveLayer(0, 0, canvas.getWidth(), canvas.getHeight(), srcPaint, Canvas.ALL_SAVE_FLAG);

            innerDrawable.draw(canvas);

            srcPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

            canvas.drawPath(srcPath, srcPaint);

            srcPaint.setXfermode(null);

            canvas.restoreToCount(saveCount);
        }
    }

    @Override
    public void setAlpha(int alpha) {
        innerDrawable.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        innerDrawable.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return innerDrawable.getOpacity();
    }
}
