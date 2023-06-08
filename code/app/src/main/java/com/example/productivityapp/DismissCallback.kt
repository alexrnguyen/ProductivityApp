package com.example.productivityapp

import com.example.productivityapp.models.Habit

/**
 *
 */
interface DismissCallback {

    fun onCallback(newHabit: Habit)
}