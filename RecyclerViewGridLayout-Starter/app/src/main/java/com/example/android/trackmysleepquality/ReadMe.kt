package com.example.android.trackmysleepquality

class ReadMe {
    /*
Ch 7-3-1
LayoutManager : manages how the items in the RecyclerView are arranged.
 LinearLayout - for horizontal and vertical lists
 GridLayout - for grids.
 More complicated use cases, you need to implement a custom LayoutManager.

 Reference website: https://developer.android.com/codelabs/kotlin-android-training-grid-layout#0

Ch 7-3-2 App overview
 介紹APP

Ch 7-3-3 Concept: Layouts and LayoutManagers (觀念重要)
1.此專案之前在 fragment_sleep_tracker.xml 加入 RecyclerView 時,是直接設定
   app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"

   LinearLayoutManager is the most common and straightforward layout manager for RecyclerView.

2. GridLayout: The GridLayoutManager for RecyclerView lays out the data as a scrollable grid, as shown below.
   GridLayout arranges items in a grid of rows and columns. Assuming vertical scrolling, by default, each item in a row takes up(接受,容納) one "span."(跨度, 廣度) (In this case, one span is equivalent to the width of one column.)

   the GridLayoutManager lays out each item in one span until the span count, which you specify. When it reaches the span count, it wraps to the next line.

   基本上,一item接受一個span, 但可以為一個item指定該item佔據的span

Ch 7-3-4 Task: Implement GridLayout
 按照步驟做
 https://developer.android.com/codelabs/kotlin-android-training-grid-layout#3
     */
}