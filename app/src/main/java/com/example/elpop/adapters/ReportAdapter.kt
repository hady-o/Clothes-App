package com.example.elpop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.elpop.data.Clothe
import com.example.elpop.data.HistoryItem
import com.example.elpop.data.Report
import com.example.elpop.databinding.ClothItemCardBinding
import com.example.elpop.databinding.HistoryItemItemCardBinding
import com.example.elpop.databinding.ReportItemCardBinding

class ReportAdapter() :
    ListAdapter<Report, ReportAdapter.ViewHolder>(ReportDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.fromViewHolder(parent)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var report = getItem(position)!!
        holder.reportBind(report)
    }


    class ViewHolder private constructor(val binding: ReportItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun fromViewHolder(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ReportItemCardBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun reportBind(
            report: Report?
        ) {
            binding.report=report
            binding.executePendingBindings()
        }
    }

    class ReportDiffCallBack : DiffUtil.ItemCallback<Report>() {
        override fun areItemsTheSame(oldItem: Report, newItem: Report): Boolean {
            return oldItem.reportId == newItem.reportId
        }

        override fun areContentsTheSame(oldItem: Report, newItem: Report): Boolean {
            return oldItem == newItem
        }

    }
}