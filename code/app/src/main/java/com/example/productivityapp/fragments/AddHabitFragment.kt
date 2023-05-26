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
import ca.antonious.materialdaypicker.MaterialDayPicker
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
                val name = binding.etHabitName.text.toString()
                val description = binding.etHabitDescription.text.toString()
                val selectedDays = binding.mdpDaysOfWeek.selectedDays

                /*val listOfIntervals = listOf("Daily", "Weekly", "2 times a week", "3 times a week",
                    "4 times a week", "5 times a week", "6 times a week", "Monthly")*/
                //binding.spinnerDurationPicker.adapter =
                val habitToAdd = Habit(name, description, selectedDays)
                habitArrayAdapter.addHabit(habitToAdd)
            }
            .setNegativeButton("Cancel", null)
            .create()
    }
}