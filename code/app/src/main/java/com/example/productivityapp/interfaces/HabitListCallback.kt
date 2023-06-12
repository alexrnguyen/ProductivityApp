package com.example.productivityapp.interfaces

import com.example.productivityapp.models.Habit

interface HabitListCallback {
    fun onGetHabits(habits: MutableList<Habit>)
}