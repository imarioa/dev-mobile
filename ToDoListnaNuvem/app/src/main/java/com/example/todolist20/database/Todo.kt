package com.example.todolist20.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Todo(
    val id: String = "",
    val title: String = "",
    var checked: Boolean = false
)
