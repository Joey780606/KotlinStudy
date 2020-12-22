package com.example.android.trackmysleepquality.sleeptracker

import android.graphics.Color
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.TextItemViewHolder
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight

class SleepNightAdapter: RecyclerView.Adapter<SleepNightAdapter.ViewHolder>()  {  // Ch7-1-5 Step 3-1
    var data = listOf<SleepNight>()  // Ch7-1-4 Step 3-3
      set(value) {
          field = value
          notifyDataSetChanged()
      }
      // Ch7-1-4 Step 3-12
      // 當data 被改變時, adapter 需讓 RecyclerView 知道, 因為 RecyclerView不知任何有關 data的事. RecyclerView只知有關 adapter 給他的 view holder
      // 當顯示的資料被改變時,為了告訴 RecyclerView, 在上面加入一個自訂的setter到 data變數
      // 在 setter, 給 data一個新值,然後呼叫 notifyDataSetChanged() 來觸發和新data 重畫list

    // Ch7-1-5 Step 2 (較怪的是不需指定 list_item_sleep_night.xml)
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sleepLength: TextView = itemView.findViewById(R.id.sleep_length)
        val quality: TextView = itemView.findViewById(R.id.quality_string)
        val qualityImage: ImageView = itemView.findViewById(R.id.quality_image)
    }

    override fun getItemCount() = data.size  // Ch7-1-4 Step 3-4
    override fun onBindViewHolder(holder: ViewHolder, position: Int) { // Ch7-1-5 Step 3-6 ~ Step 3-9
        val item = data[position]
        val res = holder.itemView.context.resources
        holder.sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
        holder.quality.text = convertNumericQualityToString(item.sleepQuality, res) // Ch7-1-5 Step 3-11
        holder.qualityImage.setImageResource(when (item.sleepQuality) {  // Ch7-1-5 Step 3-13
            0 -> R.drawable.ic_sleep_0
            1 -> R.drawable.ic_sleep_1
            2 -> R.drawable.ic_sleep_2
            3 -> R.drawable.ic_sleep_3
            4 -> R.drawable.ic_sleep_4
            5 -> R.drawable.ic_sleep_5
            else -> R.drawable.ic_sleep_active
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder { //Ch7-1-5 Step 3-2 ~ 3-5 建立item的layout
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_sleep_night, parent, false)
        return ViewHolder(view)
    }
}