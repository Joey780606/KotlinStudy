package com.example.android.trackmysleepquality.sleeptracker

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.TextItemViewHolder
import com.example.android.trackmysleepquality.database.SleepNight

class SleepNightAdapter: RecyclerView.Adapter<TextItemViewHolder>()  {  // Ch7-1-4 Step 3-2
    var data = listOf<SleepNight>()  // Ch7-1-4 Step 3-3
      set(value) {
          field = value
          notifyDataSetChanged()
      }
      // Ch7-1-4 Step 3-12
      // 當data 被改變時, adapter 需讓 RecyclerView 知道, 因為 RecyclerView不知任何有關 data的事. RecyclerView只知有關 adapter 給他的 view holder
      // 當顯示的資料被改變時,為了告訴 RecyclerView, 在上面加入一個自訂的setter到 data變數
      // 在 setter, 給 data一個新值,然後呼叫 notifyDataSetChanged() 來觸發和新data 重畫list


    override fun getItemCount() = data.size  // Ch7-1-4 Step 3-4
    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        // Ch7-1-4 Step 3-5, 3-6
        val item = data[position]
        holder.textView.text = item.sleepQuality.toString()
        // Ch7-1-4 Step 3-7, this simple example lets you see how the adapter gets the data into the view holder and onto the screen.

        if(item.sleepQuality <= 1) {
            holder.textView.setTextColor(Color.RED)  // Ch7-1-4 Step 6-4, 但因為reuse的關係,會出現不應該的值也出現紅色
        } else {
            holder.textView.setTextColor(Color.BLACK) // Ch7-1-4 Step 6-5, 即上方的改善方案
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        // Ch7-1-4 Step 3-8, 有二參數,回傳 ViewHolder
        // parent - 是 擁有(holds) view holder的view group.永遠都是 RecyclerView.
        // viewType - 當在相同 RecyclerView 裡有多個views
        // ex: 若你在相同的 RecyclerView 放了一個 text views, 一個 image, 一個video的list, 此函式將需要知道什麼型態的view被使用.

        val layoutInflater = LayoutInflater.from(parent.context)
        // Ch7-1-4 Step 3-9, layout inflater知道如何從XML layouts建立views.
        // context 含有如何正確的inflate(浮出) view. 在 recycler view的adapter, 你總是傳送 parent view group (即RecyclerView) 的context

        val view = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView
        // Ch7-1-4 Step 3-10,
        // 為 view 傳送 XML layout, 和 view 的 view group - parent變數,
        // Boolean變數 attachToRoot 需要為 false, 因為 RecyclerView 會在需要時為你加入此 item.

        return TextItemViewHolder(view)  // Ch7-1-4 Step 3-11, 回傳由 view 製造出來的 TextItemViewHolder
    }
}