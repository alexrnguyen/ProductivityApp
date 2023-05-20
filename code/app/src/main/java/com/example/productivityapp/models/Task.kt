package com.example.productivityapp.models

import java.util.Date


data class Task (
    val name: String,
    val isComplete: Boolean = false,
    val deadline: Date
)