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
     */

}