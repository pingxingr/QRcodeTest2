package com.example.qrcodetest3;

import android.content.Context;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OverlayLayout extends RelativeLayout {

    private Context mContext;
    private CustomDrawable background;

    public OverlayLayout(@NonNull Context context) {
        super(context);
        initView(context, null, 0);
    }

    public OverlayLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs, 0);
    }

    public OverlayLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        background = new CustomDrawable(getBackground());
        setBackground(background);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        resetBackground();
    }

    private void resetBackground() {
        Path path = null;
        View view = findViewById(R.id.square);
        if (view != null) {
            path = new Path();
            path.addRect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom(), Path.Direction.CW);
        }
        if (path != null) {
            background.setSrcPath(path);
        }
    }
}
