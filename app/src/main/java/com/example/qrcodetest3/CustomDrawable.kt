package com.example.qrcodetest3

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.drawable.Drawable

class CustomDrawable(private val innerDrawable: Drawable) : Drawable() {
    private val srcPaint: Paint
    private var srcPath: Path? = Path()


    init {
        srcPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        srcPaint.color = -0x80000000
    }

    fun setSrcPath(srcPath: Path) {
        this.srcPath = srcPath
    }

    override fun draw(canvas: Canvas) {
        innerDrawable.bounds = bounds
        if (srcPath == null || srcPath!!.isEmpty) {
            innerDrawable.draw(canvas)
        } else {
            val saveCount = canvas.saveLayer(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat(), srcPaint, Canvas.ALL_SAVE_FLAG)

            innerDrawable.draw(canvas)

            srcPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)

            canvas.drawPath(srcPath!!, srcPaint)

            srcPaint.xfermode = null

            canvas.restoreToCount(saveCount)
        }
    }

    override fun setAlpha(alpha: Int) {
        innerDrawable.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        innerDrawable.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return innerDrawable.opacity
    }
}
