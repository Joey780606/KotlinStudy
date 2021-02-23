/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.sleeptracker

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.trackmysleepquality.database.SleepDatabaseDao

/**
 * This is pretty much boiler plate code for a ViewModel Factory.
 *
 * Provides the SleepDatabaseDao and context to the ViewModel.
 */
class SleepTrackerViewModelFactory(
        private val dataSource: SleepDatabaseDao,
        private val application: Application) : ViewModelProvider.Factory {

    val TAG = SleepTrackerViewModelFactory::class.java!!.simpleName

    // Ch6.2.4 Step 2 - 跟 SleepTrackerViewModel 相同參數, 且繼承 ViewModelProvider.Factory
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        Log.v(TAG, "create SleepTrackerViewModelFactory")
        // Ch6.2.4 Step 2-2 - 帶任型態的參數,且回傳一個 ViewModel
        if (modelClass.isAssignableFrom(SleepTrackerViewModel::class.java)) {
            // Ch6.2.4 Step 2-3 - 檢查 modelClass 是不是可用的 SleepTrackerViewModel,是就傳回instance,
            //  不是就 throw exception (這是大部分的樣板code,可重複使用)
            Log.v(TAG, "create SleepTrackerViewModelFactory 2")
            return SleepTrackerViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

