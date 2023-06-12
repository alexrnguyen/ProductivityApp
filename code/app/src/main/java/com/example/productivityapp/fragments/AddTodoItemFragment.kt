package com.example.productivityapp.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.productivityapp.interfaces.DismissTodoCallback
import com.example.productivityapp.adapters.TodoItemArrayAdapter
import com.example.productivityapp.databinding.FragmentAddTodoItemBinding
import com.example.productivityapp.models.TodoItem
import java.time.LocalDate
import java.time.LocalTime


/**
 * A simple [Fragment] subclass.
 * Use the [AddTodoItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddTodoItemFragment(private var todoItemArrayAdapter: TodoItemArrayAdapter, private var callback: DismissTodoCallback): DialogFragment() {

    private lateinit var binding: FragmentAddTodoItemBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentAddTodoItemBinding.inflate(layoutInflater)

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .setTitle("Add To Do Item")
            .setPositiveButton("Add") {_, _ ->
                val name = binding.etTodoItemName.text.toString()

                val year = binding.dpDeadlineDate.year
                val month = binding.dpDeadlineDate.month + 1
                val day = binding.dpDeadlineDate.dayOfMonth
                val date = LocalDate.of(year, month, day)

                val hour = binding.tpDeadlineTime.hour
                val minute = binding.tpDeadlineTime.minute
                val time = LocalTime.of(hour, minute)

                val todoItem = TodoItem(name, date, time)
                todoItemArrayAdapter.addItem(todoItem)
                callback.onCallback()
            }
            .setNegativeButton("Cancel", null)
            .create()
    }
}