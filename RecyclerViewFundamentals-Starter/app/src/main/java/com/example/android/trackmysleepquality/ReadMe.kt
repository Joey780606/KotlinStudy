package com.example.android.trackmysleepquality

class ReadMe {
    /*
Ch7-1-3 Concept: RecyclerView
1. 介紹RecyclerView的精髓
2. adapter pattern - 轉接器 helps an object to work with another API.
The adapter pattern in software engineering helps an object to work with another API. RecyclerView uses an adapter to transform app data into something the RecyclerView can display, without changing how the app stores and processes the data.
3. For the sleep-tracker app, you build an adapter that adapts data from the Room database into something that RecyclerView knows how to display, without changing the ViewModel.
4. 在RecyclerView顯示資料,需要下列部分:
 a.顯示的資料
 b. A RecyclerView instance defined in your layout file, to act as the container for the views.
 c. A layout for one item of data.
 d. A layout manager: handles the organization (the layout) of UI components in a view.
 e. A view holder. extends the ViewHolder class. It contains the view information for displaying one item from the item's layout.
 f. An adapter. The adapter connects your data to the RecyclerView. It adapts the data so that it can be displayed in a ViewHolder.

Reference website: https://developer.android.com/codelabs/kotlin-android-training-recyclerview-fundamentals#2
= = = = = =
Ch7-1-4 Implement RecyclerView and an Adapter
 Step 1: Add RecyclerView with LayoutManager (在UI上加RecyclerView, xml調整屬性, 並加上 layoutManager的參數
  a.把 ScrollView 拿掉
  b.把 Palette > Containers > RecyclerView 放上去
  c. RecyclerView 要加上 app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"

 Step 2: Create the list item layout and text view holder (其中一項list item的layout)
  a.RecyclerView只是container,此步驟是建立最簡易的list item layout 和 infraructure,
    需要建一個view 和 TextView
  b. Util.kt 裡新增一個 ViewHolder 的class, 叫 TextItemViewHolder (暫時的)

 Step 3: Create SleepNightAdapter
  a. 已建view holder,和each item的layout, 再來就是建 adapter, adapter建立一個 view holder且填入 RecyclerView 要顯示的資料
  b. 建立 SleepNightAdapter, 它改寫(adapt) SleepNight object到 RecyclerView能用的事情
    Adapter需知道要用那個 view holder,所以把TextItem
  c. override 一些函式
    1) getItemCount() - to return the size of the list of sleep nights in data.  RecyclerView需知道adapter有多少item來顯示
	2) onBindViewHolder() - 被 RecyclerView呼叫, 在指定位置裡,為一個list item 來顯示資料. 所以有二參數,一個是view holder, 另一個是資料被bind的位置
	3) onCreateViewHolder() - 當 RecyclerView 需一個 view holder 來表現一個項目,會被呼叫
	  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder
	  . 有二參數,並回傳 ViewHolder
	  . parent - 是 擁有(holds) view holder的view group.永遠都是 RecyclerView.
	  . viewType - 當在相同 RecyclerView 裡有多個views
	  . ex: 若你在相同的 RecyclerView 放了一個 text views, 一個 image, 一個video的list, 此函式將需要知道什麼型態的view被使用.
  之後再檔案裡處理

 Step 4: Tell RecyclerView about the Adapter
 Step 5: Get data into the adapter
   Now you have an adapter, and a way to get data from the adapter into the RecyclerView.  Now you need to get data into the adapter from the ViewModel.
 Step 6: Explore how view holders are recycled
   RecyclerView recycles view holders, which means that it reuses them.
   Because these view holders are recycled, make sure onBindViewHolder() sets or resets any customizations that previous items might have set on a view holder.

 Reference website: https://developer.android.com/codelabs/kotlin-android-training-recyclerview-fundamentals#3

Ch7-1-5 Create a ViewHolder for all the sleep data
 1. ViewHolder的功能: A ViewHolder 描述item view和放在RecyclverView內的metadata. RecyclerView依賴此機置來正確的放置view到list scrolls. and to do interesting things like animate views when items are added or removed in the Adapter.
 View holder的itemView屬性: 若RecyclerView不需存取ViewHolder儲存的views, 能使用view holder的itemView屬性. 當在螢幕上顯示binding的item, RecyclerView uses itemView, when drawing decorations around a view like a border, and for implementing accessibility.

 Step 1: 建立item的layout
 Ch7-1-5 Step 1 建立item的layout

  Reference website: https://developer.android.com/codelabs/kotlin-android-training-recyclerview-fundamentals#4

     */
}