package com.example.productivityapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.productivityapp.interfaces.DismissCallback
import com.example.productivityapp.R
import com.example.productivityapp.adapters.HabitArrayAdapter
import com.example.productivityapp.database.Database
import com.example.productivityapp.databinding.FragmentHabitPageBinding
import com.example.productivityapp.models.Habit

class HabitFragment(private val habitArrayAdapter: HabitArrayAdapter, private val pos: Int, private val callbackToTracker: DismissCallback): Fragment() {

    private lateinit var binding: FragmentHabitPageBinding

    private lateinit var habit: Habit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHabitPageBinding.inflate(layoutInflater)
        habit = habitArrayAdapter.getItem(pos)
        initializeFragment(habit)

        // TODO: Clicking a day should have no effect on the Habit Page
        binding.mdpDaysOfWeek.isEnabled = false

        // Update fragment when returning from the EditHabitFragment (changes have likely been made)
        val callback = object : DismissCallback {
            override fun onCallback(newHabit: Habit) {
                habit = newHabit
                initializeFragment(habit)
            }
        }

        // Handle button clicks
        binding.ibBackButton.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
            callbackToTracker.onCallback(habit)
        }

        binding.ibEditButton.setOnClickListener {
            val dialog = EditHabitFragment(habitArrayAdapter, pos, habit, callback)
            dialog.show((it.context as FragmentActivity).supportFragmentManager, "Edit Habit")
        }

        binding.ibCompleteButton.setOnClickListener {
            binding.pbHabitProgress.progress = habit.updateProgress()
            val db = Database.getInstance()
            db?.editHabit(habit.name, habit)
            setProgressHeader()
        }

        return binding.root
    }

    /**
     *
     */
    private fun initializeFragment(habit: Habit) {
        binding.tvHabitName.text = habit.name
        binding.tvHabitDescription.text = habit.description
        binding.mdpDaysOfWeek.setSelectedDays(habit.selectedDays)
        binding.pbHabitProgress.progress = habit.timesCompleted

        // Set the number of repetitions needed to complete a habit based on the repetition interval
        if(habit.repetitionInterval == "Daily" || habit.repetitionInterval == "Weekly" || habit.repetitionInterval == "Monthly") {
            binding.pbHabitProgress.max = 1
        }
        else {
            binding.pbHabitProgress.max = habit.selectedDays.size
        }
        setProgressHeader()
    }

    private fun setProgressHeader() {
        binding.tvProgressHeader.text = getString(R.string.progress_bar_header, habit.timesCompleted, binding.pbHabitProgress.max)
    }

}