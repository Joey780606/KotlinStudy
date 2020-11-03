package com.example.android.navigation;

/*
 Ch3-3-3 重點筆記
  網址: https://codelabs.developers.google.com/codelabs/kotlin-android-training-start-external-activity/index.html#2
Safe args - NavDirection (一旦 gradle 加入了safeargs, Safe args plugin就會為每個Fragment建立 NavDirection, 以此例來說,GameFragment 有一個 GameFragmentdirections class,可用此在other fragments來傳遞)
為何要用Safe args plugin?
 之前建議用Bundle,但會遇到二個問題
  a. Type mismatch errors.
  b. Missing key errors.
 所以在 Android's Navigation Architecture component包函了Safe Args.
 Safe Args is a Gradle plugin that generates code and classes that help detect errors at compile-time that might not otherwise be surfaced until the app runs.
 =========================

 Ch3-3-4
  網址: https://codelabs.developers.google.com/codelabs/kotlin-android-training-start-external-activity/index.html#3
  1. Use GameFragmentDirections method in the GameFragment to send argument to GameWonFragment.
  2. Replace Fragment classes with NavDirection classes.
 ==========================

 Ch3-3-5
  網址: https://codelabs.developers.google.com/codelabs/kotlin-android-training-start-external-activity/index.html#4
  1. Implicit intent: Android allows you to use intents to navigate to activities that other apps provide.
   (簡例就是如果想拍照,不用知道那些app支援拍照,若有多個app能handle相同的implicit intent,Android就會顯示chooser讓使用者選擇)


 */
class Readme {

}
