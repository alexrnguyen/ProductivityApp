package com.example.productivityapp.models

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class TodoItem (
    var name: String,
    var dueDate: LocalDate,
    var dueTime: LocalTime,
    var isComplete: Boolean = false
)