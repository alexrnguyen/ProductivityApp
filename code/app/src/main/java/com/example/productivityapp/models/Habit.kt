package com.example.productivityapp.models

import android.util.Log
import ca.antonious.materialdaypicker.MaterialDayPicker
import java.util.Date

data class Habit (
    var name: String,
    var description: String,
    var selectedDays: List<MaterialDayPicker.Weekday>,
    var repetitionInterval: String,
    var isComplete: Boolean = false,
    var timesCompleted: Int = 0,
) {

    fun updateProgress(): Int {
        Log.d("Progress", timesCompleted.toString())
        if (!isComplete) {
            timesCompleted += 1

            if(checkIfCompleted(timesCompleted)) {
                isComplete = true
            }
        }
        return timesCompleted
    }

    private fun checkIfCompleted(progress: Int): Boolean {
        return if(repetitionInterval == "Daily" || repetitionInterval == "Weekly" || repetitionInterval == "Monthly") {
            progress >= 1
        } else {
            progress >= selectedDays.size
        }
    }
}