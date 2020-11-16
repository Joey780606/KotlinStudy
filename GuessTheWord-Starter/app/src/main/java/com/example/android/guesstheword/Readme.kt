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

   ==============
   Ch5-2-1 LiveData and LiveData observers (Welcome)
   Learn how to integrate LiveData with the data in the ViewModel classes.
     a. LiveData, which is one of the Android Architecture Components, lets you build data objects that notify views when the underlying database changes.
     b. Use the LiveData class, you set up "observers" (for example, activities or fragments) that observe changes in the app's data.
   關鍵字: LiveData, MutableLiveData

   Reference website: https://codelabs.developers.google.com/codelabs/kotlin-android-training-live-data#0
   ==============
   Ch5-2-2 無重點
   ==============
   Ch5-2-3 無重點
   ==============
   Ch5-2-4 Add LiveData to the GameViewModel
   1. 深入介紹LiveData
     LifecycleOwner (LiveData 觀察者 與 LifecycleOwner (通常是Activity or Fragment)結合. LiveData 只會在 observer是 STARTED 或 RESUMED 時更新資料

   2. 會做的事:
     a. How to wrap any data type into LiveData objects by converting the current score and current word data in the GameViewModel to LiveData. (用 MutableLiveData,看來是這節會做的事)
     b. Add an observer to these LiveData objects and learn how to observe the LiveData.

   3. MutableLiveData
     MutableLiveData is a LiveData whose value can be changed. MutableLiveData is a generic class, so you need to specify the type of data that it holds.

   關鍵字: LifecycleOwner, MutableLiveData, LiveData
   Reference website: https://codelabs.developers.google.com/codelabs/kotlin-android-training-live-data#3
   ==============
   Ch5-2-5 Attach observers to the LiveData objects
   1. viewLifecycleOwner的介紹 (有介紹,很重要)
   2. 建立 observe 的函式,內有 Observer 的參數

   關鍵字: viewLifecycleOwner, observe, Observer
   Reference website: https://codelabs.developers.google.com/codelabs/kotlin-android-training-live-data#4
   ===============
   Ch5-2-6 Encapsulate the LiveData
   1. Encapsulation
   2. MutableLiveData(可改變) 和 LiveData(可讀,但不可改變) 的差異
   3. backing property
   避免 score, word 直接被任何程式使用,所以使用backing property的方式,將變數封裝起來,讓別的程式只能使用get的方式存取

   關鍵字 : ViewModel, MutableLiveData, LiveData, backing property
   Reference website: https://codelabs.developers.google.com/codelabs/kotlin-android-training-live-data#5

   ================
   Ch5-2-7 Add a game-finished event
   1. 希望全部猜完後,會直接跳到Game finished那頁
   2. You use the LiveData observer pattern to model a game-finished event.
   3. Observer pettern的說明 an observable (the "subject" of observation) and observers.
   4. In the case of LiveData in this app, the observable (subject) is the LiveData object, and the observers are the methods in the UI controllers, such as fragments.

   Step 2
   5. LiveData delivers updates to the observers only when data changes. An exception to this behavior is that observers also receive updates when the observer changes from an inactive to an active state. (如果螢幕轉向,就屬於from an inactive to an active state. 這時LiveData就會再傳送update到 observers 目前狀態一次)
   6. 如果螢幕轉向等from inactive to active狀態,要記得將相關的變數再還原一次,避免相關的處理會再出現一次

   Keyword: Design pattern, backing property, observe
   Reference website: https://codelabs.developers.google.com/codelabs/kotlin-android-training-live-data#6

   ===============
   Ch5-2-8 Add LiveData to the ScoreViewModel
   1. 對 ScoreViewModel 的 LiveData 進行調整, 將相關參數改成 LiveData來處理

   Reference website: https://codelabs.developers.google.com/codelabs/kotlin-android-training-live-data#7

   ===============
   Ch5-2-9 Add the play again button
   1. 在 score fragment加入一個Play again button,利用 LiveData 再加一個判斷 Play again的處理

   Reference website: https://codelabs.developers.google.com/codelabs/kotlin-android-training-live-data#8
 */