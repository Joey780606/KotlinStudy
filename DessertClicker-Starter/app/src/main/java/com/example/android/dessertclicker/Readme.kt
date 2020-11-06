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

==========
4-2-4
  1. lifecycle library, which is part of Android Jetpack, simplifies this task
  2. Three main parts of the lifecycle library:
    a. Lifecycle owners, which are the components that have (and thus "own") a lifecycle. Activity and Fragment are lifecycle owners. Lifecycle owners implement the LifecycleOwner interface.
    b. The Lifecycle class, which holds the actual state of a lifecycle owner and triggers events when lifecycle changes happen.
    c. Lifecycle observers, which observe the lifecycle state and perform tasks when the lifecycle changes. Lifecycle observers implement the LifecycleObserver interface.
  3. 將 DessertTimer 設成 LifecycleObserver (Ch 4.2.4 - Step 1) (文件描述重要)
  4. FragmentActivity superclass implements LifecycleOwner. 所以 MainActivity 不需做 LifecycleOwner的事
  Reference website: https://codelabs.developers.google.com/codelabs/kotlin-android-training-complex-lifecycle/index.html#3

==========
4-2-5
  1. 前言:Shutdown的機制
  2. Step 1: 如何在 Android studio 的 Terminal tab中執行 adb
    adb shell am kill com.example.android.dessertclicker
      - 刪除app, com.example.android.dessertclicker 是 package name (但在 ubuntu 上發現實作失敗,不確定是否是版本問題)
  3. Step 2: Use onSaveInstanceState() to save bundle data
    在 Activity stopped時會執行 onSaveInstanceState()
  4. Step 2-4: Bundle的介紹, 很重要
    精華: a. A bundle is a collection of key-value pairs, where the keys are always strings. You can put primitive values, such as int and boolean values, into the bundle.
    b. Because the system keeps this bundle in RAM, it's a best practice to keep the data in the bundle small.
    c. Generally you should store far less than 100k, otherwise you risk crashing your app with the TransactionTooLargeException error.
  5. Step 3 教導如何用 onCreate()來判斷app是 第一次開,或是重新啟動

  Reference website: https://codelabs.developers.google.com/codelabs/kotlin-android-training-complex-lifecycle/index.html#4

=========
Ch4-2-6
  1. Configuration change (螢幕轉向,切換語言等)會讓
  2. A configuration change happens when the state of the device changes so radically that the easiest way for the system to resolve the change
     is to completely shut down and rebuild the activity.(像這時會重建activity,表示邏輯確實不能加在activity裡)
  此段只有教學,程式沒有改變

  Reference website: https://codelabs.developers.google.com/codelabs/kotlin-android-training-complex-lifecycle/index.html#5
 */