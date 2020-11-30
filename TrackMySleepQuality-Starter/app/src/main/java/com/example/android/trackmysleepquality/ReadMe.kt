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
     */
}