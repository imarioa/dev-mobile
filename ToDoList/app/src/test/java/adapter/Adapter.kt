package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import data.TodoData
import kotlinx.android.synthetic.main.itemtodo.view.*

class Adapter(var todos: List<TodoData>): RecyclerView.Adapter<Adapter.TodoViewHolder>(){
    inner class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemtodo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.itemView.apply {
            texttitle.text = todos[position].title
            checkb.isChecked = todos[position].checked
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}