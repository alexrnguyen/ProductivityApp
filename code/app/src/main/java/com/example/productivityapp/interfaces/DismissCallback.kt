package com.example.productivityapp.interfaces

import com.example.productivityapp.models.Habit

/**
 *
 */
interface DismissCallback {
    fun onCallback(newHabit: Habit)
}