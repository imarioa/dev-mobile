package com.example.todolist20.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.todolist20.database.Todo
import com.example.todolist20.database.TodoDAO
import kotlinx.coroutines.launch


class TodoViewModel(private val todoItem: TodoDAO): ViewModel() {
    private var _allitems = MutableLiveData<List<Todo>>()
    val allItems: LiveData<List<Todo>>
    get() {
        todoItem.getItems { _allitems.value = it }
        return _allitems
    }

    fun addNewItem(itemName: String) {
        val newItem = getNewItemEntry(itemName)
        insertItem(newItem)
    }

    private fun insertItem(item: Todo) {
        todoItem.insert(item)
    }
    private var _selectedItem = MutableLiveData<Todo>()


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
        itemId: String,
        title: String,
        checked: Boolean
    ) {
        val updatedItem = getUpdatedItemEntry(itemId, title, checked)
        updateItem(updatedItem)
    }
    private fun updateItem(item: Todo) {
            todoItem.update(item)
    }
    private fun getUpdatedItemEntry(
        itemId: String,
        title: String,
        checked: Boolean
    ): Todo {
        return Todo(
            id = itemId,
            title = title,
            checked = checked
        )
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