package com.example.todolist20.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist20.database.Todo
import androidx.recyclerview.widget.ListAdapter
import com.example.todolist20.database.TodoDAO
import com.example.todolist20.databinding.ItemtodoBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class Adapter():
    ListAdapter<Todo,Adapter.AdapterViewHolder>(DiffCallback) {

    lateinit var ref = Firebase.database.reference.


    class AdapterViewHolder(private var binding: ItemtodoBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(todo: Todo){
                todoDAO
                binding.todotitle.text = todo.title
                binding.checkb.isChecked = todo.checked
            }
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(ItemtodoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

     override fun onBindViewHolder(viewHolder: AdapterViewHolder, position: Int){
        val current = getItem(position)
         viewHolder.bind(current)
    }



    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Todo>() {
            override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }
}