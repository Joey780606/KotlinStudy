package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter     //ListAdapter 是要 import 這個
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding

//class SleepNightAdapter: RecyclerView.Adapter<SleepNightAdapter.ViewHolder>()  {  // Ch7-1-5 Step 3-1
class SleepNightAdapter: ListAdapter<SleepNight, SleepNightAdapter.ViewHolder>(SleepNightDiffCallback())  {  // Ch7-2-5 Step 1
    // Ch7-1-5 Step 2 (較怪的是不需指定 list_item_sleep_night.xml)
    class ViewHolder public constructor(val binding: ListItemSleepNightBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SleepNight) {
            binding.sleep = item
            binding.executePendingBindings()
        }
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) { // Ch7-1-5 Step 3-6 ~ Step 3-9
        val item = getItem(position)
        val res = holder.itemView.context.resources
//        holder.binding.sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
//        holder.binding.qualityString.text = convertNumericQualityToString(item.sleepQuality, res) // Ch7-1-5 Step 3-11
//        holder.binding.qualityImage.setImageResource(when (item.sleepQuality) {  // Ch7-1-5 Step 3-13
//            0 -> R.drawable.ic_sleep_0
//            1 -> R.drawable.ic_sleep_1
//            2 -> R.drawable.ic_sleep_2
//            3 -> R.drawable.ic_sleep_3
//            4 -> R.drawable.ic_sleep_4
//            5 -> R.drawable.ic_sleep_5
//            else -> R.drawable.ic_sleep_active
//        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder { //Ch7-1-5 Step 3-2 ~ 3-5 建立item的layout
        val layoutInflater = LayoutInflater.from(parent.context)
        //val view = layoutInflater.inflate(R.layout.list_item_sleep_night, parent, false)
        val binding = ListItemSleepNightBinding.inflate(layoutInflater, parent, false)
        //return ViewHolder(view)
        return ViewHolder(binding)
    }

    class SleepNightDiffCallback : DiffUtil.ItemCallback<SleepNight>() {
        override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
            return oldItem.nightId == newItem.nightId
        }

        override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
            return oldItem == newItem
        }
    }
}