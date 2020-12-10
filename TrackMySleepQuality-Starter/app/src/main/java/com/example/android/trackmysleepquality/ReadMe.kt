package com.example.android.trackmysleepquality

class ReadMe {
    /*
Ch6-1-1 Welcome
 1. Room is a database library that's part of Android Jetpack. Room takes care of many of the chores of setting up and configuring a database, and makes it possible for your app to interact with the database using ordinary function calls. Under the hood, Room is an abstraction layer on top of an SQLite database.

 2. 示意圖重要
 Reference website: https://developer.android.com/codelabs/kotlin-android-training-room-database#0

============
Ch6-1-2 App overview
APP說明

 Reference website: https://developer.android.com/codelabs/kotlin-android-training-room-database#1

============
Ch6-1-3 Task: Download and inspect the starter app
 1.照做把app copy進來
 2.Step 2 Inspect the startup app 重要,如果 "Android" 該頁看不到相關的資料,可以切到 "Project" 來看,就可以找的到

 Reference website: https://developer.android.com/codelabs/kotlin-android-training-room-database#2

============
Ch6-1-4 Create the SleepNight entity
 1. 在 database 需要 entities 和 queries.
  相關介紹在文件有寫
  You must define each entity as an annotated data class, and the interactions as an annotated interface,
  a data access object (DAO). Room uses these annotated classes to create tables in the database, and queries that act on the database.

 2. 實際在 database package 裡的 SleepNight 來新增 Table

 Reference website: https://developer.android.com/codelabs/kotlin-android-training-room-database#3

============
Ch6-1-5 Create the DAO
 1. DAO (Data access object) - On Android, the DAO provides convenience methods for inserting, deleting, and updating the database.
 2. Think of a DAO as defining a custom interface for accessing your database.
 3. Room library provides convenience annotations, such as @Insert, @Delete, and @Update. For everything else, there is the @Query annotation.
 4. Step 1: SleepDatabaseDao 這個DAO 是一個 interface
 5. 用到的 annotation
  @Dao (Step 1-2)
  @Insert (Step 1-3)
  @Update (Step 1-4)
  @Query (Step 1-5 ~ 1-9) 各種不同的Query範例

  Reference website: https://developer.android.com/codelabs/kotlin-android-training-room-database#4

=============
Ch6-1-6 Create and test a room database
    文章說這是 Database 的參考範本,要多學起來
    1. In this task, you create a Room database that uses the Entity and DAO that you created in the previous task.
    2. 使用到的annotation: @Database
    3. 專有名詞: database holder, singleton
    4. Create a public abstract class that extends RoomDatabase. This class is to act as a database holder. The class is abstract, because Room creates the implementation for you.

    Step 2是unit test範例
    1. SleepDatabaseTest是 test class
    2. @RunWith - 定義 test runner, 是設定和執行 tests的程式
    3. @Before - 建立一個記憶體內的 SleepDatabase 和 SleepDatabaseDao
      In-memory 表示 database 不是存在 file system且在test執行後被刪除
    4. allowMainThreadQueries - 允許你執行在main thread執行 test
    5. @Test - 建立,插入,存取 SleepNight, assert(宣稱)他們是一樣的,
       若有錯會傳出 exception,在實例中,你可能會有多個@Test
    6. @After ,Testing結束後,用來執行關閉資料庫
    7. 測試方式重要,參Step 2的4,5

    Reference website: https://developer.android.com/codelabs/kotlin-android-training-room-database#5

=============
Ch6-2-3 Task: Inspect the starter code
    1. 注意 activity_main.xml, 它包含 nav_host_fragment, 也注意 <merge>
      The merge tag can be used to eliminate(消除) redundant layouts when including layouts, and it's a good idea to use it. An example of a redundant layout would be ConstraintLayout > LinearLayout > TextView, where the system might be able to eliminate the LinearLayout. This kind of optimization can simplify the view hierarchy and improve app performance
    2. 注意 fragment_sleep_tracker.xml (依文章指示)

    Refererence website: https://developer.android.com/codelabs/kotlin-android-training-coroutines-and-room#2

=============
Ch6-2-4 Add a ViewModel
    1. you need to collect data, add the data to the database, and display the data. All this work is done in the view model. (所在的整段都很重要)
    2. ViewModel 的實作過程,很重要

    Reference website: https://developer.android.com/codelabs/kotlin-android-training-coroutines-and-room#3

=============
Ch 6-2-5 Coroutines  (整章都重要,只介紹,沒有更新Code)
    1. In Kotlin, coroutines are the way to handle long-running tasks elegantly and efficiently. Kotlin coroutines let you convert callback-based code to sequential code. Code written sequentially is typically easier to read and can even use language features such as exceptions.

    In the end, coroutines and callbacks do the same thing: they wait until a result is available from a long-running task and continue execution.

    2. Async is that you cannot expect that the result is available, until you explicitly wait for it. (有個範例不錯)

    3. Non-blocking : means that a coroutine does not block the main or UI thread. So with coroutines, users always have the smoothest possible experience, because the UI interaction always has priority.

    4. Suspend: Kotlin的key word

    5. Blocking 和 Suspending的差異:
     If a thread is blocked, no other work happens. If the thread is suspended, other work happens until the result is available.

    6. 使用Kotlin需要的三個東西
     a. job : Basically, a job is anything that can be canceled. Every coroutine has a job, and you can use the job to cancel the coroutine.
     b. dispatcher : The dispatcher sends off coroutines to run on various threads.
       Dispatchers.Main runs tasks on the main thread
       Dispatchers.IO offloads blocking I/O tasks to a shared pool of threads.
     c. scope : A coroutine's scope defines the context in which the coroutine runs.

    7. Kotlin coroutines with Architecture components
     a. CoroutineScope 的介紹
     b. Architecture components
       ViewModelScope : A ViewModelScope is defined for each ViewModel in your app. (這堂課會用到的)
    8. Room library 是使用 Dispatchers.IO 在背景執行database operations. 你不用明確的指定 Dispatchers.

    Reference website: https://developer.android.com/codelabs/kotlin-android-training-coroutines-and-room#4

Ch 6-2-6 Collect and display the data
    1. 功能新增 - Start, Stop, Clear 按鍵按下後要做的事情
    2. Note that a coroutine with Room uses Dispatchers.IO, so this will not happen on the main thread.
    3. 文中 "Important: Now you can see a pattern:" 的文字重要:
      a. key word: CoroutineScope, viewModelScope(在 SleepTrackerViewModel.kt 裡有用到), ViewModel
      b. withContext(Dispatchers.ID) - 是若一個需等較長時間回的部分(ex:向網站要資料),要用此來等待回覆?
    4. Step 4 - 要宣告一變數來記錄table裡所有的資料 (nights) (重要,把資料庫記錄變成文字的部分很重要)
      a. Transformation map 可用來來把所 table 資料,轉換成字串等的資料
      b. Spanned - HTML-格式的字串
      c. string.xml CDATA 來格式化字串resource來顥示sleep data.
    5. Step 5 - 處理 Stop button
      a. return@label 指定要針對那個 function 做 return 的回傳

    Reference website: https://developer.android.com/codelabs/kotlin-android-training-coroutines-and-room#5
     */

}