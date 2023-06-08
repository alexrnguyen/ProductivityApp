package com.example.productivityapp.fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import ca.antonious.materialdaypicker.DefaultSelectionMode
import ca.antonious.materialdaypicker.SingleSelectionMode
import com.example.productivityapp.DismissCallback
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
class EditHabitFragment(private var habitArrayAdapter: HabitArrayAdapter, private val pos: Int, private var habit: Habit, private val callback: DismissCallback) : DialogFragment() {

    private lateinit var binding: FragmentEditHabitBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //var rootView: View = inflater.inflate(R.layout.fragment_add_habit, container, false)

        // TODO: Need to refactor since most of this is duplicated from the AddHabitFragment file
        binding = FragmentEditHabitBinding.inflate(layoutInflater)
        binding.etHabitName.setText(habit.name)
        binding.etHabitDescription.setText(habit.description)
        binding.mdpDaysOfWeek.setSelectedDays(habit.selectedDays)

        val listOfIntervals = resources.getStringArray(R.array.list_of_intervals)
        binding.spinnerDurationPicker.adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item, listOfIntervals)

        val spinnerPosition = binding.spinnerDurationPicker.selectedItemPosition
        binding.spinnerDurationPicker.setSelection(spinnerPosition)

        binding.spinnerDurationPicker.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                //Default settings (should see day picker and be able to select multiple days by default)
                binding.mdpDaysOfWeek.visibility = View.VISIBLE
                binding.mdpDaysOfWeek.selectionMode = DefaultSelectionMode.create()

                if(binding.spinnerDurationPicker.selectedItem.toString() == "Weekly") {
                    binding.mdpDaysOfWeek.selectionMode = SingleSelectionMode.create()
                }
                else if(binding.spinnerDurationPicker.selectedItem.toString() == "Daily") {
                    binding.mdpDaysOfWeek.selectAllDays()
                }
                else if(binding.spinnerDurationPicker.selectedItem.toString() == "Monthly") {
                    binding.mdpDaysOfWeek.visibility = View.INVISIBLE
                }
                else {
                    //Multiple times a week option selected
                    if(binding.mdpDaysOfWeek.selectedDays.size < 2) {
                        Log.d("Test", "Need to select more days!!!")
                    }
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .setTitle("Edit Habit")
            .setPositiveButton("Save") {_, _ ->
                val name = binding.etHabitName.text.toString()
                val description = binding.etHabitDescription.text.toString()
                val selectedDays = binding.mdpDaysOfWeek.selectedDays

                val repetitionInterval = binding.spinnerDurationPicker.selectedItem.toString()
                val habitToChange = Habit(name, description, selectedDays, repetitionInterval)
                habitArrayAdapter.editHabit(habitToChange, pos)
                callback.onCallback(habitToChange)
            }
            .setNegativeButton("Cancel", null)
            .create()
    }
}