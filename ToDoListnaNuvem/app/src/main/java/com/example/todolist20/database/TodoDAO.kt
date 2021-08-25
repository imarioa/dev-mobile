package com.example.todolist20.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.Flow



class TodoDAO(private val db: DatabaseReference) {

    private val listenerTracker = HashMap<DatabaseReference, ValueEventListener>()

    fun getItems(callback: (List<Todo>) -> Unit){
        db.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = mutableListOf<Todo>()

                for(child in snapshot.children){
                    items.add(child.getValue(Todo::class.java)!!)
                }
                val oldListener = listenerTracker.put(snapshot.ref,this)
                if(oldListener != null){
                    db.removeEventListener(oldListener)
                }else{
                    print("lol")
                }
                callback(items)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    fun getITem(id: String, callback: (Todo) -> Unit){
        db.child(id).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val newTodo = snapshot.getValue(Todo::class.java)!!
                val oldListener = listenerTracker.put(snapshot.ref,this)
                if(oldListener != null){
                    db.child(id).removeEventListener(oldListener)
                }
                callback(newTodo)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

     fun insert(item: Todo){
        val key = db.push().key
        if(key != null) {
            db.child(key).setValue(item.copy(id = key))
        }
    }

     fun update(item: Todo, map: Map<String, Any> = mapOf()){
        if(map.isEmpty()) {
            db.child("Todo").child(item.id).setValue(item)
        }else{
            db.child("Todo").child(item.id).updateChildren(map)
        }
    }

    fun delete(item: Todo){
        db.child(item.id).removeValue()
    }
    fun removeAllListeners(){
        for(listener in listenerTracker.values){
            db.removeEventListener(listener)
        }
        listenerTracker.clear()
    }
}