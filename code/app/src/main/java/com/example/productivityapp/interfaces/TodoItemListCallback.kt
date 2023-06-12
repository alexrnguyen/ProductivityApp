package com.example.productivityapp.interfaces

import com.example.productivityapp.models.TodoItem

interface TodoItemListCallback {
    fun onCallback(todoItems: MutableList<TodoItem>)
}