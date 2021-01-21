package com.joey.minipaint

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.view.View
import androidx.core.content.res.ResourcesCompat

private const val STROKE_WIDTH = 12f   // Horse important : 若放到class裡, const會出錯,Why?

class MyCanvasView(context: Context) : View(context) {  // Horse important : 需要 context 參數
    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap

    private val drawColor = ResourcesCompat.getColor(resources, R.color.colorPaint, null)
    private val backgroundColor = ResourcesCompat.getColor(resources, R.color.colorBackground, null)

    private var path = Path()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (::extraBitmap.isInitialized) extraBitmap.recycle()

        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(backgroundColor)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(extraBitmap, 0f, 0f, null) // Horse important : 0f, 0f mean top left corner
    }

    private val paint = Paint().apply {
        color = drawColor
        isAntiAlias = true  // Smooths out edges of what is drawn without affecting shape.
        isDither = true     // Dithering affects how colors with higher-precision than the device are down-sampled.
        style = Paint.Style.STROKE      // default: FILL
        strokeJoin = Paint.Join.ROUND   // default: MITER
        strokeCap = Paint.Cap.ROUND     // default: BUTT
        strokeWidth = STROKE_WIDTH      // default: Hairline-width (really thin)
    }
}