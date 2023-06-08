package com.example.productivityapp.database

import com.example.productivityapp.models.Habit

interface HabitListCallback {
    fun onGetHabits(habits: MutableList<Habit>)
}