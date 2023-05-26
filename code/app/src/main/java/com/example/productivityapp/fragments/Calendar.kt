package com.example.productivityapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productivityapp.EventList
import com.example.productivityapp.R
import com.example.productivityapp.adapters.HourArrayAdapter
import com.example.productivityapp.databinding.FragmentCalendarBinding
import com.example.productivityapp.databinding.ItemHourCellBinding
import com.example.productivityapp.models.HourEvent
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [Calendar.newInstance] factory method to
 * create an instance of this fragment.
 */
class Calendar : Fragment() {

    private lateinit var binding: FragmentCalendarBinding
    private lateinit var hourArrayAdapter: HourArrayAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCalendarBinding.inflate(layoutInflater)

        // Display current date
        val calendar = java.util.Calendar.getInstance(TimeZone.getDefault())
        val currentYear = calendar.get(java.util.Calendar.YEAR)
        val currentMonth = calendar.get(java.util.Calendar.MONTH)
        val currentDay = calendar.get(java.util.Calendar.DAY_OF_MONTH)
        val monthText = Month.values()[currentMonth].toString()
        binding.tvDate.text = String.format("%s %d %d", monthText, currentDay, currentYear)

        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        Log.d("Time", LocalDateTime.now().format(formatter))

        // Display events scheduled for current date
        val hourEventList = hourEventList()
        hourArrayAdapter = HourArrayAdapter(hourEventList)
        binding.rvEventList.adapter = hourArrayAdapter
        binding.rvEventList.layoutManager = LinearLayoutManager(context)

        binding.calendar.setOnDateChangeListener { _, year, month, day ->
            val newMonthText = Month.values()[month].toString()
            binding.tvDate.text = String.format("%s %d %d", newMonthText, day, year)

            // Display events schedule for new date


        }

        return binding.root
    }


    private fun hourEventList(): MutableList<HourEvent> {
        val hourEventList = mutableListOf<HourEvent>()
        for(hour in 0..23) {
            val time = LocalTime.of(hour, 0)
            Log.d("Hour", time.toString())
            val events = EventList()
            events.eventsForDateAndTime(Instant.ofEpochMilli(binding.calendar.date).atZone(ZoneId.systemDefault()).toLocalDate(), time)
            val hourEvent = HourEvent(time, mutableListOf())
            hourEventList.add(hourEvent)
        }
        return hourEventList
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment Calendar.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            Calendar().apply {

            }
    }
}