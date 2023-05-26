package com.example.productivityapp.models

import java.time.LocalDate
import java.time.LocalTime
import kotlin.time.Duration

data class Event (
    val name: String,
    val date: LocalDate,
    val time: LocalTime,
    val duration: Duration
)