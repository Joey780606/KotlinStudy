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

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding

//Ch 7-5-4 Step 3-1
private val ITEM_VIEW_TYPE_HEADER = 0
private val ITEM_VIEW_TYPE_ITEM = 1

//class SleepNightAdapter(val clickListener: SleepNightListener) :
//        ListAdapter<SleepNight, SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()) {   // Ch7-4-4 Step 2 - 2

class SleepNightAdapter(val clickListener: SleepNightListener) :
        ListAdapter<SleepNightAdapter.DataItem, RecyclerView.ViewHolder>(SleepNightDiffCallback()) {   // Ch7-4-5 Step 3
    //Ch 7-5-4 Step 1 (整個Step 1都在這裡)
    sealed class DataItem {
        abstract val id: Long   //Ch 7-5-4 Step 1-6, 若加這個, 下面的SleepNightItem 和 Header沒有再 override val id,這二個function就會錯誤
        data class SleepNightItem(val sleepNight: SleepNight): DataItem() { //Ch 7-5-4 Step 1-4
            override val id = sleepNight.nightId
        }
        object Header: DataItem() {//Ch 7-5-4 Step 1-5
            override val id = Long.MIN_VALUE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val nightItem = getItem(position) as DataItem.SleepNightItem
                holder.bind(nightItem.sleepNight, clickListener)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> ViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    fun addHeaderAndSubmitList(list: List<SleepNight>?) {
        val items = when(list) {
            null -> listOf(DataItem.Header)
            else -> listOf(DataItem.Header) + list.map { DataItem.SleepNightItem(it)  }
        }
        submitList(items)
    }

    //Ch 7-5-4 Step 2-3
    class TextViewHolder(view: View): RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): TextViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.header, parent, false)
                return TextViewHolder(view)
            }
        }
    }

    class ViewHolder private constructor(val binding: ListItemSleepNightBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: SleepNight, clickListener: SleepNightListener) { // Ch7-4-4 Step 2-5
            binding.sleep = item
            binding.executePendingBindings()
            binding.clickListener = clickListener  // Ch7-4-4 Step 2-5
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemSleepNightBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    // Ch 7-5-4 Step 3-2
    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.SleepNightItem -> ITEM_VIEW_TYPE_ITEM
        }
    }
}


class SleepNightDiffCallback : DiffUtil.ItemCallback<SleepNightAdapter.DataItem>() {
    override fun areItemsTheSame(oldItem: SleepNightAdapter.DataItem, newItem: SleepNightAdapter.DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: SleepNightAdapter.DataItem, newItem: SleepNightAdapter.DataItem): Boolean {
        return oldItem == newItem
    }
}

//Ch7-4-4 Step 1 - 1 ~ 5
class SleepNightListener(val clickListener : (sleepId: Long) -> Unit) {
    fun onClick(night: SleepNight) = clickListener(night.nightId)
}