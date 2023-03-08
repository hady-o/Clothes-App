package com.example.elpop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.elpop.data.Clothe
import com.example.elpop.data.Person
import com.example.elpop.databinding.ClothItemCardBinding
import com.example.elpop.databinding.PersoncardBinding

class PersonAdapter(val clickListener: PersonListenerClass) :
    ListAdapter<Person, PersonAdapter.ViewHolder>(PersonDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.fromViewHolder(parent)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var person = getItem(position)!!
        holder.personBind(person,clickListener)
        holder.itemView.setOnClickListener{
            clickListener.onClick(person)
        }
    }
    class ViewHolder private constructor(val binding: PersoncardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun fromViewHolder(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PersoncardBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun personBind(
            person: Person?,clickListener: PersonListenerClass
        ) {
            binding.person = person
            binding.salary.text= person!!.salary.toString()
            binding.executePendingBindings()
        }
    }

    class PersonDiffCallBack : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }

    }

    class PersonListenerClass(val clickListener: (person:Person) -> Unit) {
        fun onClick(person:Person) = clickListener(person)
    }
}