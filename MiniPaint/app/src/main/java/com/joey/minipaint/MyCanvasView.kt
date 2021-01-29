package com.joey.minipaint

import android.content.Context
import android.graphics.*
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.core.content.res.ResourcesCompat

// Ch2.2.5 Step 1-1
private const val STROKE_WIDTH = 12f   // Horse important : 若放到class裡, const會出錯,Why?

class MyCanvasView(context: Context) : View(context) {  // Horse important : 需要 context 參數
    private val TAG = "MyCanvasView"
    // Ch2.2.4 Step 1-1
    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap    // Horse important : 這只是畫背景的顏色而已

    // Ch2.2.5 Step 1-2
    private val drawColorY = ResourcesCompat.getColor(resources, R.color.colorPaint, null)

    // Ch2.2.4 Step 1-2
    private val backgroundColor = ResourcesCompat.getColor(resources, R.color.colorBackground, null)

    private var motionTouchEventX = 0f
    private var motionTouchEventY = 0f

    private var currentX = 0f
    private var currentY = 0f

    // Ch2.2.5 Step 2
    private var path = Path()

    private val touchTolerance = ViewConfiguration.get(context).scaledPagingTouchSlop

    private lateinit var frame: Rect

    // Ch2.2.4 Step 1-3
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) { //Horse important: Important function
        super.onSizeChanged(w, h, oldw, oldh)
        Log.v(TAG, "onSizeChanged 00:width:$w, height:$h, $oldw, $oldh") //Horse important: log
        if (::extraBitmap.isInitialized) {  //Horse important: 檢查lateinit var是否已初始化,很少看到此事發生,但因為下方有個 Bitmap.createBitmap, 避免Memory leak,所以要做此檢查
            Log.v(TAG, "onSizeChanged 01:$w, $h, $oldw, $oldh")
            extraBitmap.recycle()
        }

        //Horse important: 以下3行是建用bitmap建Canvas的方式,是要繪背景色
        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(backgroundColor)
        extraCanvas.hashCode()
        Log.v(TAG, "canvas check01:" + extraCanvas.hashCode())  //Horse important: log,經確認與 onDraw 的 canvas非同一個

        val inset = 40
        frame = Rect(inset, inset, width - inset, height - inset)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.v(TAG, "canvas check02:" + canvas.hashCode())
        //Horse important: 需要用View的canvas,把我們畫的canvas再放進去,但是語法會是drawBitmap, 參 Android快速開發 文件
        canvas?.drawBitmap(extraBitmap, 0f, 0f, null) // Horse important : 0f, 0f mean top left corner
        canvas?.drawRect(frame, paint)
    }

    // Ch2.2.5 Step 1-3
    private val paint = Paint().apply {
        color = drawColorY
        isAntiAlias = true  // Smooths out edges of what is drawn without affecting shape.
        isDither = true     // Dithering affects how colors with higher-precision than the device are down-sampled.
        style = Paint.Style.STROKE      // default: FILL
        strokeJoin = Paint.Join.ROUND   // default: MITER
        strokeCap = Paint.Cap.ROUND     // default: BUTT
        strokeWidth = STROKE_WIDTH      // default: Hairline-width (really thin)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {  // Horse important : Touch event
        //return super.onTouchEvent(event)
        motionTouchEventX = event?.x
        motionTouchEventY = event?.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart()
            MotionEvent.ACTION_MOVE -> touchMove()
            MotionEvent.ACTION_UP -> touchUp()
        }
        return true
    }

    private fun touchStart() {  //Ch2.2.6 Step 2, 按下時執行
        Log.v(TAG, "touch Start")
        path.reset()
        path.moveTo(motionTouchEventX, motionTouchEventY)
        currentX = motionTouchEventX
        currentY = motionTouchEventY
    }

    private fun touchMove() {   // Ch2.2.6 Step 3
        val dx = Math.abs(motionTouchEventX - currentX)
        val dy = Math.abs(motionTouchEventY - currentY)
        var sLog = "touch Move ";
        if(dx >= touchTolerance || dy >= touchTolerance) {
            sLog += "$dx , $dy , $touchTolerance"  // Horse important
            // QuadTo() adds a quadratic bezier from the last point,
            // approaching control point (x1,y1), and ending at (x2,y2).
            path.quadTo(currentX, currentY, (motionTouchEventX + currentX) / 2, (motionTouchEventY + currentY) / 2)
            currentX = motionTouchEventX
            currentY = motionTouchEventY
            // Draw the path in the extra bitmap to cache it.
            extraCanvas.drawPath(path, paint)
        }
        Log.v(TAG, sLog)
        invalidate()
    }

    private fun touchUp() {   // Ch2.2.6 Step 4
        Log.v(TAG, "touch up")
        // Reset the path so it doesn't get drawn again.
        path.reset()
    }
}