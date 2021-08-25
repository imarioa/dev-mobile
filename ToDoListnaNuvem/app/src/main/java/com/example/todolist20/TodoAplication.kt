package com.example.todolist20

import android.app.Application
import com.example.todolist20.database.TodoDatabase

class TodoAplication: Application() {
    val database: TodoDatabase by lazy { TodoDatabase() }
}