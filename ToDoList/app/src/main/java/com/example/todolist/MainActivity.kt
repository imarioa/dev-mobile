package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.adapter.Adapter
import com.example.todolist.data.TodoData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = Adapter(mutableListOf())
        rcTodo.adapter = adapter
        rcTodo.layoutManager = LinearLayoutManager(this)
        additembt.setOnClickListener {
            if(edtext.text.toString().isNotBlank()){
                val title = edtext.text.toString()
                val todo = TodoData(title, false)
                adapter.addTodo(todo)
                edtext.text.clear()
            }
        }
        deleteitembt.setOnClickListener {
            adapter.deleteTodo()
        }
    }
}