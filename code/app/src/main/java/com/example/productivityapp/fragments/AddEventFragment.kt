package com.example.productivityapp.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.productivityapp.adapters.HourArrayAdapter
import com.example.productivityapp.databinding.FragmentAddEventBinding
import com.example.productivityapp.models.Event
import java.time.LocalDate
import java.time.LocalTime


/**
 * A simple [Fragment] subclass.
 * Use the [AddEventFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddEventFragment(private var hourArrayAdapter: HourArrayAdapter): DialogFragment() {

    private lateinit var binding: FragmentAddEventBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentAddEventBinding.inflate(layoutInflater)

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .setTitle("Add Event")
            .setPositiveButton("Add") {_, _ ->
                val name = binding.etEventName.text.toString()

                val year = binding.dpEventDate.year
                val month = binding.dpEventDate.month + 1
                val day = binding.dpEventDate.dayOfMonth
                val date = LocalDate.of(year, month, day)

                val hour = binding.tpEventTime.hour
                val minute = binding.tpEventTime.minute
                val time = LocalTime.of(hour, minute)

                val event = Event(name, date, time)

                hourArrayAdapter.addEventToEvents(event, hour)
                // TODO: Implement a callback (similar to EditHabitFragment)
            }
            .setNegativeButton("Cancel", null)
            .create()
    }
}