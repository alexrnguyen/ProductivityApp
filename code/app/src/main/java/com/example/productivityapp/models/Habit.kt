package com.example.productivityapp.models

import ca.antonious.materialdaypicker.MaterialDayPicker
import java.util.Date

data class Habit (
    var name: String,
    var description: String,
    var selectedDays: List<MaterialDayPicker.Weekday>,
    var repetitionInterval: String = "Weekly", // Possibly use kotlin Duration class (Weekly by default temporarily)
    var isComplete: Boolean = false,
)