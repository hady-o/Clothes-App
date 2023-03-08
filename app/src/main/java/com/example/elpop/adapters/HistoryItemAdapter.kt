package com.example.elpop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.elpop.data.Clothe
import com.example.elpop.data.HistoryItem
import com.example.elpop.databinding.ClothItemCardBinding
import com.example.elpop.databinding.HistoryItemItemCardBinding
import com.example.elpop.ui.home.HomeFragmentDirections
import com.example.elpop.ui.home.history.HistoryInfoFragmentDirections

class HistoryItemAdapter() :
    ListAdapter<HistoryItem, HistoryItemAdapter.ViewHolder>(HistoryItemDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.fromViewHolder(parent)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = getItem(position)!!
        holder.itemBind(item)
    }


    class ViewHolder private constructor(val binding: HistoryItemItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun fromViewHolder(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HistoryItemItemCardBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun itemBind(
            historyItem: HistoryItem?
        ) {
            binding.name.setText(historyItem!!.name)
            binding.price.setText(historyItem!!.price.toString())
            binding.quantity.setText(historyItem!!.quantity.toString())
            binding.total.setText((historyItem!!.price*historyItem.quantity).toString())
            binding.executePendingBindings()
            binding.edit.setOnClickListener(){
                binding.root.findNavController().navigate(HistoryInfoFragmentDirections.actionHistoryInfoFragmentToHistoryItemEditFragment(historyItem))
            }
        }
    }

    class HistoryItemDiffCallBack : DiffUtil.ItemCallback<HistoryItem>() {
        override fun areItemsTheSame(oldItem: HistoryItem, newItem: HistoryItem): Boolean {
            return oldItem.hId == newItem.hId
        }

        override fun areContentsTheSame(oldItem: HistoryItem, newItem: HistoryItem): Boolean {
            return oldItem == newItem
        }

    }
}