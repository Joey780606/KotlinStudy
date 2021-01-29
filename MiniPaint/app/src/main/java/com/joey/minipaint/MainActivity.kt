package com.joey.minipaint

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

/*
  // 重要: Horse important
  https://developer.android.com/codelabs/advanced-android-kotlin-training-canvas#0

  Ch2.2.1 看過2次,重要,原理
  Ch2.2.2 看過2次,不重要
  Ch2.2.3 看過2次,頗重要,主要是在主畫面畫滿整個View
  Ch2.2.4 看過2次,很重要,尤其是Canvas的建立,與View在onDraw()時,要把View Canvas再畫一次Canvas的Bitmap的觀念
          onSizeChanged(), onDraw()
  Ch2.2.5 看完2次,重點在建立繪圖的Paint
  Ch2.2.6 看過2次,很重要,onTouchEvent() Touch螢幕會處理的事(test)
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        val myCanvasView = MyCanvasView(this)

        // Horse important : Original set is deprecated
        //myCanvasView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false) // Horse important: 設 true 或 false實作都沒影響
        } else {
            myCanvasView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        myCanvasView.contentDescription = getString(R.string.canvasContentDescription)
        setContentView(myCanvasView)
    }
}