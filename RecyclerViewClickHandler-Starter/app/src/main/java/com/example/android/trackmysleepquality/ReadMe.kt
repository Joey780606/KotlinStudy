package com.example.android.trackmysleepquality

class ReadMe {
    /*
Ch 7-4-1 Welcome
 1. 學習對 RecyclerView 的項目進行click listener的動作
 https://developer.android.com/codelabs/kotlin-android-training-interacting-with-items/#0

Ch 7-4-2 App overview
 1. App簡單說明
 https://developer.android.com/codelabs/kotlin-android-training-interacting-with-items/#1

Ch 7-4-3 Task: Get the starter code and inspect the changes to the app
 1. 使用新的starter app,並檢查(inspect)app的改變
 https://developer.android.com/codelabs/kotlin-android-training-interacting-with-items/#2

Ch 7-4-4 Task: Make items clickable (非常重要)
 1. click listen 和 操作相關處理是 二部分的task ;First, you need to listen to and receive the click and determine which item has been clicked. Then, you need to respond to the click with an action.
 2. 提到 ViewHolder 是最佳的click listen位置,但不是最佳的操作(回應)點
 3. Adapter 可以用來操作 clicks. 但 adapter只負責顯示, 不處理 app的logic. 所以需放操作在ViewModel 裡
 4. 按照程式流程做
  https://developer.android.com/codelabs/kotlin-android-training-interacting-with-items/#3

Ch 7-4-5 Task: Handle item clicks
 1. 處理按下 Recyclerview 內的 item時,要怎麼做處理
 2. 最後的 Handle null values in the binding adapters: 重要,如何避免 is null的crash項目.
 Reference website: https://developer.android.com/codelabs/kotlin-android-training-interacting-with-items/#4

Ch 7-5-1 Headers in RecyclerView
 要學的是,在RecyclerView裡,使用超過一個ViewHolder來在不同的layout裡加一項目. 如何使用第二個ViewHolder來,在RecyclerView的items顯示上方加入header

Ch 7-5-2 不重要

Ch 7-5-3 Concept: Headers in RecyclerView
 1. 文中提到數次 different ViewHolder
 2. 加入 headers的二種方法,此處將使用第一種方法

 Reference website: https://developer.android.com/codelabs/kotlin-android-training-headers/#2

Ch 7-5-4 Task: Add a header to your RecyclerView
 1. Sealed class: https://medium.com/@carterchen247/kotlin%E4%BD%BF%E7%94%A8%E5%BF%83%E5%BE%97-sealed-class-82eccf890ac0
 2. 有用到 DiffUtil, DiffItemCallback
 3. SleepNightAdapter.kt 改很多,要研究

 https://developer.android.com/codelabs/kotlin-android-training-headers/#3
     */
}