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

package com.example.android.trackmysleepquality.sleepquality

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.SleepDatabase
import com.example.android.trackmysleepquality.databinding.FragmentSleepQualityBinding
import com.example.android.trackmysleepquality.sleeptracker.SleepTrackerViewModelFactory

/**
 * Fragment that displays a list of clickable icons,
 * each representing a sleep quality rating.
 * Once the user taps an icon, the quality is set in the current sleepNight
 * and the database is updated.
 */
class SleepQualityFragment : Fragment() {
    val TAG = SleepQualityFragment::class.java!!.simpleName
    /**
     * Called when the Fragment is ready to display content to the screen.
     *
     * This function uses DataBindingUtil to inflate R.layout.fragment_sleep_quality.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        Log.v(TAG, "create SleepQualityFragment")
        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentSleepQualityBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_sleep_quality, container, false)

        val application = requireNotNull(this.activity).application

        //Ch6-3-4 Step 2-2,但依AS建議調整了一下
        val arguments = SleepQualityFragmentArgs.fromBundle(requireArguments())

        //Ch6-3-4 Step 2-3
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao

        //Ch6-3-4 Step 2-4
        val viewModelFactory = SleepQualityViewModelFactory(arguments.sleepNightKey, dataSource)

        //Ch6-3-5 Step 2-5
        val sleepQualityViewModel = ViewModelProvider(this, viewModelFactory).get(SleepQualityViewModel::class.java)

        //Ch6-3-5 Step 2-6 sleepQualityViewModel會是紅字,之後會在 fragment_sleep_quality.xml 加入
        binding.sleepQualityViewModel = sleepQualityViewModel

        //Ch6-3-7 Step 2-7
        sleepQualityViewModel.navigateToSleepTracker.observe(this, Observer {
            if(it == true) {
                this.findNavController().navigate(
                    SleepQualityFragmentDirections.actionSleepQualityFragmentToSleepTrackerFragment())
            }
        })
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(TAG, "create onDestroy SleepQualityFragment")
    }
}
