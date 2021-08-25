package com.example.todolist20.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist20.database.Todo
import com.example.todolist20.database.TodoDAO
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope


class TodoViewModel(private val todoItem: TodoDAO): ViewModel() {

    val allItems: LiveData<List<Todo>> = todoItem.getItems().asLiveData()

    fun addNewItem(itemName: String) {
        val newItem = getNewItemEntry(itemName)
        insertItem(newItem)
    }

    private fun insertItem(item: Todo) {
        viewModelScope.launch {
            todoItem.insert(item)
        }
    }
    private fun getNewItemEntry(itemName: String): Todo {
        return Todo(
            title = itemName,
            checked = false,
        )
    }
    fun isEntryValid(todoTitle: String): Boolean {
        if (todoTitle.isBlank()) {
            return false
        }
        return true
    }
    fun updateItem(
        itemId: Int,
        title: String,
        checked: Boolean
    ) {
        val updatedItem = getUpdatedItemEntry(itemId, title, checked)
        updateItem(updatedItem)
    }
    private fun updateItem(item: Todo) {
        viewModelScope.launch {
            todoItem.update(item)
        }
    }
    private fun getUpdatedItemEntry(
        itemId: Int,
        title: String,
        checked: Boolean
    ): Todo {
        return Todo(
            id = itemId,
            title = title,
            checked = checked
        )
    }

    fun deleteChecked(){
        viewModelScope.launch {
            Log.v("Delete","Vai caceta")
            todoItem.deleteChecked()
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            todoItem.delete(todo)
        }
    }

    class TodoViewModelFactory(private val item: TodoDAO) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return TodoViewModel(item) as T
            }
            throw IllegalArgumentException("Unknow viewmodel class")
        }
    }
}