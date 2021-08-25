package com.example.todolist20.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class TodoDatabase {
    val database: DatabaseReference by lazy { Firebase.database.reference }

    fun itemFirebaseRepository() = TodoDAO(database.child("Todo"))
}