package com.example.android.guesstheword

class Readme {
}

/*
  Ch5-1-3 Explore the starter code
    只有介紹程式架構,沒有修改程式

  ==================
  Ch5-1-4 Find problems in the starter app (只有介紹,沒有修改程式）
  1.點出專案問題
  2.onSaveInstanceState() callback
  3.介紹App architecture (MVVV- model-view-viewmodel)
  4.UI controller (ViewModel & ViewModelFactory) (介紹重要)
  5.ViewModel的介紹(重要) GameViewModel的使用
  6.ViewModelFactory 實例化(instantiates) ViewModel object.

  https://codelabs.developers.google.com/codelabs/kotlin-android-training-view-model/#3

  ==================
  Ch5-1-5 Create the GameViewModel
  The ViewModel class 是設計來儲存和管理UI-related data. 在本例, 一個ViewModel對一個fragment (規則應也是一 ViewModel 對一 Fragment).
    Step 1: 加入 GameViewModel class (按步驟走就對了)
    Step 2: Override onCleared() 且加入 logging
      The ViewModel is destroyed when the associated fragment is detached, or when the activity is finished. (fragment被detach 或 activity被結束時, ViewModel會跟著結束)
      要 override onCleared(),來追蹤 GameViewModel的lifecycle.
    Step 3: 結合 GameViewModel 與 Game fragment. (在 GameFragment.kt 加 ViewModel 的變數)
      UI controller - 就是 activity 或 fragment 的意思
    Step 4: Initialize the ViewModel
      1. 像 screen rotations, UI controller會重建,但 ViewModel instances會存活,
      2. ViewModelProvider :
        a. 若使用ViewModel class 來建立 ViewModel, 若每次fragment重建時,每次都會建立new object. 所以要換另種方式, 使用 ViewModelProvider 來建立ViewModel instance.
        b. ViewModelProvider的使用方式 (看 How ViewModelProvider works)
      3. 使用 ViewModelProvider.get() 來 建立 ViewModelProvider
        在 onCreateView() 來 initialize viewModel

   Reference code: https://codelabs.developers.google.com/codelabs/kotlin-android-training-view-model/#4

   ==============
   Ch5-1-6 Populate the GameViewModel

   1. ViewModel 的特性再介紹
   2. 把資料從 GameFragment 移到 GameViewModel
    GameFragmentBinding - Do not move the binding variable, GameFragmentBinding, because it contains references to the views. This variable is used to :
      a. inflate the layout,
      b. set up the click listeners,
      c. display the data on the screen—responsibilities of the fragment
   3. 把 UI 的資料轉到 GameViewModel 的 init block. because you should reset the word list when the ViewModel is created, not every time the fragment is created.

   Reference code: https://codelabs.developers.google.com/codelabs/kotlin-android-training-view-model/#5

   ==============
   Ch5-1-7 Implement click listener for the End Game button

   1. 對 "End Game" button實行click listener.
   2. 在第3項,使用 Safe Args. (可回頭參考 Ch3-3-3)
      在 gameFinished() 函式 使用 NavHostFragment 等來跳頁

   Reference website: https://codelabs.developers.google.com/codelabs/kotlin-android-training-view-model/#6

   ==============
   Ch5-1-8 Use a ViewModelFactory
   1. factory method pattern
     The factory method pattern is a creational design pattern that uses factory methods to create objects. A factory method is a method that returns an instance of the same class.
   2. 步驟4 - Under the score package, create another Kotlin class called ScoreViewModelFactory. This class will be responsible for instantiating the ScoreViewModel object.
   3. ViewModelProvider.Factory 的相關處理
      這節比較難,要花時間多研究

   Reference website: https://codelabs.developers.google.com/codelabs/kotlin-android-training-view-model/#7
 */