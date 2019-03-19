package com.example.qrcodetest3

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import java.io.IOException


class QRcodeScanningProcessor(context: Context) : VisionProcessorBase<List<FirebaseVisionBarcode>>() {

    var context: Context

    companion object {
        private const val TAG = "QRcodeScanProcessor"
    }

    init {
        this.context = context
    }

    private val detector: FirebaseVisionBarcodeDetector by lazy {
        val options: FirebaseVisionBarcodeDetectorOptions = FirebaseVisionBarcodeDetectorOptions.Builder()
             .setBarcodeFormats(FirebaseVisionBarcode.FORMAT_QR_CODE)
             .build()
        FirebaseVision.getInstance().getVisionBarcodeDetector(options)
    }

    override fun stop() {
        try {
            detector.close()
        } catch (e: IOException) {
            Log.e(TAG, "Exception thrown while trying to close Barcode Detector: $e")
        }
    }

    override fun detectInImage(image: FirebaseVisionImage): Task<List<FirebaseVisionBarcode>> {
        return detector.detectInImage(image)
    }

    override fun onSuccess(
        originalCameraImage: Bitmap?,
        barcodes: List<FirebaseVisionBarcode>,
        frameMetadata: FrameMetadata,
        graphicOverlay: GraphicOverlay
    ) {
        graphicOverlay.clear()

        originalCameraImage?.let {
            val imageGraphic = CameraImageGraphic(graphicOverlay, it)
            graphicOverlay.add(imageGraphic)
        }

        barcodes.forEach {
            val barcode: FirebaseVisionBarcode = it;
            QRCodeScanActivity.setTextView(barcode.rawValue)
            (context as Activity).finish()
        }
        graphicOverlay.postInvalidate()
    }

    override fun onFailure(e: Exception) {
        Log.e(TAG, "QRcode detection failed $e")
    }
}