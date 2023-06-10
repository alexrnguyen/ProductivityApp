package com.example.productivityapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.productivityapp.databinding.ItemTodoBinding
import com.example.productivityapp.models.HourEvent
import com.example.productivityapp.models.TodoItem

class TodoItemArrayAdapter(private val todoItemArray: MutableList<TodoItem>): RecyclerView.Adapter<TodoItemArrayAdapter.TodoItemViewHolder>() {

    class TodoItemViewHolder(binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        var name = binding.tvTodoItemName

    }

    private lateinit var binding: ItemTodoBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TodoItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return todoItemArray.size
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        val currentItem = todoItemArray[position]

        holder.itemView.apply {
            binding.tvTodoItemName.text = currentItem.name
        }

        holder.itemView.setOnClickListener {

        }
    }

    fun addItem(todoItem: TodoItem) {
        todoItemArray.add(todoItem)
        notifyItemInserted(todoItemArray.size - 1)
    }

    fun getItem(position: Int): TodoItem {
        return todoItemArray[position]
    }

    fun removeAt(position: Int) {
        todoItemArray.removeAt(position)
        notifyItemRemoved(position)
    }
}