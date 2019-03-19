package com.example.qrcodetest3

import android.content.Context
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout

class QRcodeOverlayLayout : RelativeLayout {

    private val mContext: Context? = null
    private var background: QRcodeDrawable? = null

    constructor(context: Context) : super(context) {
        initView(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context, attrs)
    }


    private fun initView(context: Context, attrs: AttributeSet?) {
        background = QRcodeDrawable(getBackground())
        setBackground(background)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        resetBackground()
    }

    private fun resetBackground() {
        var path: Path? = null
        val view = findViewById<View>(R.id.rectangle)
        if (view != null) {
            path = Path()
            path.addRect(view.left.toFloat(), view.top.toFloat(), view.right.toFloat(), view.bottom.toFloat(), Path.Direction.CW)
        }
        if (path != null) {
            background!!.setSrcPath(path)
        }
    }
}
