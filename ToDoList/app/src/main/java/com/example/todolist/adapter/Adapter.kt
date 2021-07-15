package com.example.todolist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.data.TodoData
import kotlinx.android.synthetic.main.itemtodo.view.*

class Adapter(private val todos: MutableList<TodoData>): RecyclerView.Adapter<Adapter.TodoViewHolder>(){
    class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    fun addTodo(todo: TodoData){
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }
    fun deleteTodo(){
       todos.removeAll { todo ->
           todo.checked
       }
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemtodo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.itemView.apply {
            texttitle.text = todos[position].title
            checkb.isChecked = todos[position].checked
            checkb.setOnClickListener {
                todos[position].checked = !todos[position].checked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}