package com.example.productivityapp.fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.productivityapp.R
import com.example.productivityapp.adapters.HabitArrayAdapter
import com.example.productivityapp.databinding.FragmentAddHabitBinding
import com.example.productivityapp.models.Habit

/**
 * A simple [Fragment] subclass.
 * Use the [AddHabitFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddHabitFragment(private var habitArrayAdapter: HabitArrayAdapter) : DialogFragment() {

    private lateinit var binding: FragmentAddHabitBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentAddHabitBinding.inflate(layoutInflater)

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .setTitle("Add Habit")
            .setPositiveButton("Add") {_, _ ->
                val daysOfWeek = binding.mdpDaysOfWeek.selectedDays
                print(daysOfWeek.toString())
                val habitToAdd = Habit(binding.etHabitName.text.toString(), binding.etHabitDescription.text.toString())
                habitArrayAdapter.addHabit(habitToAdd)
            }
            .setNegativeButton("Cancel", null)
            .create()
    }
}