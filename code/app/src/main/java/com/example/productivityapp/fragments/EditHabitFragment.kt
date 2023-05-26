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
import com.example.productivityapp.databinding.FragmentEditHabitBinding
import com.example.productivityapp.models.Habit

/**
 * A simple [Fragment] subclass.
 * Use the [AddHabitFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditHabitFragment(private var habitArrayAdapter: HabitArrayAdapter, private var position: Int) : DialogFragment() {

    private lateinit var binding: FragmentEditHabitBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //var rootView: View = inflater.inflate(R.layout.fragment_add_habit, container, false)
        var habit = habitArrayAdapter.getItem(position)
        binding = FragmentEditHabitBinding.inflate(layoutInflater)
        binding.etHabitName.setText(habit.name)
        binding.etHabitDescription.setText(habit.description)
        binding.mdpDaysOfWeek.setSelectedDays(habit.selectedDays)

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .setTitle("Edit Habit")
            .setPositiveButton("Save") {_, _ ->
                habit.name = binding.etHabitName.text.toString()
                habit.description = binding.etHabitDescription.text.toString()
                habit.selectedDays = binding.mdpDaysOfWeek.selectedDays
                habitArrayAdapter.notifyItemChanged(position)
            }
            .setNegativeButton("Cancel", null)
            .create()
    }
}