package com.example.elpop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.elpop.data.Clothe
import com.example.elpop.data.History
import com.example.elpop.databinding.ClothItemCardBinding
import com.example.elpop.databinding.HistoryItemCardBinding

class HistoryAdapter(val clickListener: HistoryListenerClass) :
    ListAdapter<History, HistoryAdapter.ViewHolder>(HistoryDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.fromViewHolder(parent)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var history = getItem(position)!!
        holder.historyBind(history,clickListener)
        holder.itemView.setOnClickListener{
            clickListener.onClick(history)
        }
    }


    class ViewHolder private constructor(val binding: HistoryItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun fromViewHolder(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HistoryItemCardBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun historyBind(
            history: History?,clickListener: HistoryListenerClass
        ) {
            binding.id.text=history!!.historyId.toString()
            binding.total.text = history!!.total.toString()
            binding.date.text = history.date
        }
    }

    class HistoryDiffCallBack : DiffUtil.ItemCallback<History>() {
        override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
            return oldItem.historyId == newItem.historyId
        }

        override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
            return oldItem == newItem
        }

    }

    class HistoryListenerClass(val clickListener: (history:History?) -> Unit) {
        fun onClick(history: History?) = clickListener(history)
    }
}