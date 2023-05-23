package com.example.productivityapp.models

import java.util.Date

data class Habit (
    var name: String,
    var description: String,
    var isComplete: Boolean = false,
)