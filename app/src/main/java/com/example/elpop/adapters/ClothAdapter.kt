package com.example.elpop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.elpop.data.Clothe
import com.example.elpop.databinding.ClothItemCardBinding

class ClothAdapter(val clickListener: ClothListenerClass) :
    ListAdapter<Clothe, ClothAdapter.ViewHolder>(ClothDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.fromViewHolder(parent)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var cloth = getItem(position)!!
        holder.clothBind(cloth,clickListener)
        holder.itemView.setOnClickListener{
            clickListener.onClick(cloth)
        }
    }


    class ViewHolder private constructor(val binding: ClothItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun fromViewHolder(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ClothItemCardBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun clothBind(
            cloth:Clothe?,clickListener: ClothListenerClass
        ) {
            binding.cloth =cloth
            binding.clothPricr.text= cloth!!.price.toString() + " "+"eg"
            binding.executePendingBindings()
        }
    }

    class ClothDiffCallBack : DiffUtil.ItemCallback<Clothe>() {
        override fun areItemsTheSame(oldItem: Clothe, newItem: Clothe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Clothe, newItem: Clothe): Boolean {
            return oldItem == newItem
        }

    }

    class ClothListenerClass(val clickListener: (cloth:Clothe) -> Unit) {
        fun onClick(cloth:Clothe) = clickListener(cloth)
    }
}