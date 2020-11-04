package com.example.android.dessertclicker

/*
4-1-3
基本的 lifecycle 介紹和 log

===========
4-1-4
  1. 加入 Timber (log用)
  2. 建立 Application class (Timber需要使用Application class)
  3. 實作 Timber
  Reference website: https://codelabs.developers.google.com/codelabs/kotlin-android-training-lifecycles-logging/index.html#3

===========
4-1-5 (無程式修改)
  1. 按 Back 鍵做 Garbage collect的動作
  2. onRestart() - onCreate 或 onRestart() 會讓app開起來, onCreate()只會出現一次,其他的就是由 onRestart() 來開啟
  3. 此例無程式變化
  Reference website: https://codelabs.developers.google.com/codelabs/kotlin-android-training-lifecycles-logging/index.html#4

==========
4-2-3
  1.只是做 timer 處理的測試,沒有真正的做法,目前只有在 onStart() 做 dessertTimer.startTimer(), onStop() 做 dessertTimer.stopTimer();
  2. Step 2-4 有 Processes and threads的link,有空可以看
  3. Step 2-10略提到 leak的問題
  Reference website: https://codelabs.developers.google.com/codelabs/kotlin-android-training-complex-lifecycle/index.html#2
 */