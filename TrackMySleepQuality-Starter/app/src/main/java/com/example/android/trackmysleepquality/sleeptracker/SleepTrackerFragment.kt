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

import android.os.Bundle
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
import com.example.android.trackmysleepquality.databinding.FragmentSleepTrackerBinding

/**
 * A fragment with buttons to record start and end times for sleep, which are saved in
 * a database. Cumulative data is displayed in a simple scrollable TextView.
 * (Because we have not learned about RecyclerView yet.)
 */
class SleepTrackerFragment : Fragment() {

    /**
     * Called when the Fragment is ready to display content to the screen.
     *
     * This function uses DataBindingUtil to inflate R.layout.fragment_sleep_quality.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentSleepTrackerBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_sleep_tracker, container, false)

        val application = requireNotNull(this.activity).application // Ch6.2.4 Step 3-1, 若是null 回傳 IllegalArgumentException

        // Create an instance of the ViewModel Factory
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao // Ch6.2.4 Step 3-2, 沒有會幫他建立一個
        val viewModelFactory = SleepTrackerViewModelFactory(dataSource, application) // Ch6.2.4 Step 3-3

        // Get a reference to the ViewModel associated with this fragment
        val sleepTrackerViewModel =
                ViewModelProvider(this, viewModelFactory).get(SleepTrackerViewModel::class.java)    // Ch6.2.4 Step 3-4

        binding.setLifecycleOwner(this) // Ch6.2.4 Step 4-1
        binding.sleepTrackerViewModel = sleepTrackerViewModel  // Ch6.2.4 Step 4-2

        sleepTrackerViewModel.navigateToSleepQuality.observe(this, Observer { // Ch6.3.5 Step 2-5, 2-6
            night ->
            night?.let {
                this.findNavController().navigate(
                        SleepTrackerFragmentDirections.actionSleepTrackerFragmentToSleepQualityFragment(night.nightId))
                sleepTrackerViewModel.doneNavigating()
            }
        })
        return binding.root
    }
}
