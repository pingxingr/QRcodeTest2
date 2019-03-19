package com.example.qrcodetest3

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect

/** Draw camera image  */
class CameraImageGraphic(overlay: GraphicOverlay, private val bitmap: Bitmap) : GraphicOverlay.Graphic(overlay) {

    override fun draw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, null, Rect(0, 0, canvas.width, canvas.height), null)
    }
}

