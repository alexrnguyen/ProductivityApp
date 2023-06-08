package com.example.productivityapp

import com.example.productivityapp.models.Event
import java.time.LocalDate
import java.time.LocalTime

class EventList {
    private var events = mutableListOf<Event>()

    fun eventsForDateAndTime(date: LocalDate, time: LocalTime) {
        for(event in events) {
            val eventHour = event.time.hour
            val cellHour = time.hour
            if(event.date == date && eventHour == cellHour) {
                events.add(event)
            }
        }
    }

    fun get(index: Int): Event {
        return events[index]
    }

    fun size(): Int {
        return events.size
    }

    fun addEvent(eventToAdd: Event) {
        events.add(eventToAdd)
    }

    fun removeEvent(eventToRemove: Event) {
        events.remove(eventToRemove)
    }
}