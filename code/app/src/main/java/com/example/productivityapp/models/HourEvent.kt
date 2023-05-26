package com.example.productivityapp.models

import java.time.LocalTime

data class HourEvent (
        val time: LocalTime,
        val events: MutableList<Event>
)
