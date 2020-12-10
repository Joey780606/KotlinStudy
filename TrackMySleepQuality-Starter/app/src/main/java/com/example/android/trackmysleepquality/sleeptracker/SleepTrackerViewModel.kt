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
import android.provider.SyncStateContract.Helpers.update
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.formatNights
import kotlinx.coroutines.launch

/**
 * ViewModel for SleepTrackerFragment.
 */
class SleepTrackerViewModel(
        val database: SleepDatabaseDao,
        application: Application) : AndroidViewModel(application) {
    // Ch6.2.4 Step 1 - 繼承 AndroidViewModel, 使用 application context為 constructor 參數

    private var tonight = MutableLiveData<SleepNight?>()  //Ch6-2-6 Step 2-4
    private val nights = database.getAllNights()    // Ch6-2-6 Step 4-4

    val nightsString = Transformations.map(nights) { nights ->  //Ch 6-2-6 Step 4-5
        formatNights(nights, application.resources)
    }

    init {  //Ch6-2-6 Step 2-5
        initializeTonight()
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun onStartTracking() {  //Ch6-2-6 Step 3-1
        viewModelScope.launch { //Ch6-2-6 Step 3-2
            val newNight = SleepNight() //Ch6-2-6 Step 3-3
            insert(newNight)  //Ch6-2-6 Step 3-4
        }
    }

    private suspend fun insert(night: SleepNight) { //Ch6-2-6 Step 3-6
        database.insert(night)  //Ch6-2-6 Step 3-7
    }

    fun onStopTracking() {  //Ch6-2-6 Step 5-1
        viewModelScope.launch {
            val oldNight = tonight.value ?: return@launch
            oldNight.endTimeMilli = System.currentTimeMillis()
            update(oldNight)
        }
    }

    private suspend fun update(night: SleepNight) {  //Ch6-2-6 Step 5-2
        database.update(night)
    }

    fun onClear() {   //Ch6-2-6 Step 6-1
        viewModelScope.launch {
            clear()
            tonight.value = null
        }
    }

    private suspend fun clear() {   //Ch6-2-6 Step 6-2
        database.clear()
    }

    private fun initializeTonight() {   //Ch6-2-6 Step 2-6 : viewModelScope可看Ch6-2-5的介紹
        viewModelScope.launch {
            tonight.value = getTonightFromDatabase() //Ch6-2-6 Step 3-5
        }
    }

    private suspend fun getTonightFromDatabase(): SleepNight? { //Ch6-2-6 Step 2-7
        var night = database.getTonight()
        if(night?.endTimeMilli != night?.startTimeMilli)
            night = null
        return night
    }
}

