package com.example.productivityapp.models

import com.example.productivityapp.EventList
import java.time.LocalTime

data class HourEvent (
        val time: LocalTime,
        val events: MutableList<Event>
)
