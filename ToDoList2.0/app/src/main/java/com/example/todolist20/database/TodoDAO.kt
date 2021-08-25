package com.example.todolist20.database

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface TodoDAO {
    @Query("SELECT * from todo ORDER BY title ASC")
    fun getItems(): Flow<List<Todo>>

    @Query("SELECT * from todo WHERE id = :id")
    fun getITem(id: Int): LiveData<Todo>

    @Query("DELETE from todo")
    suspend fun deleteChecked()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Todo)

    @Update
    suspend fun update(item: Todo)

    @Delete
    suspend fun delete(item: Todo)
}