package com.example.productivityapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.productivityapp.databinding.ItemHourCellBinding
import com.example.productivityapp.models.Event
import com.example.productivityapp.models.HourEvent
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class HourArrayAdapter(private val hourEventArray: MutableList<HourEvent>): RecyclerView.Adapter<HourArrayAdapter.HourEventViewHolder>() {
    class HourEventViewHolder(binding: ItemHourCellBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    private lateinit var binding: ItemHourCellBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourEventViewHolder {
        binding = ItemHourCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return HourEventViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return hourEventArray.size
    }

    override fun onBindViewHolder(holder: HourEventViewHolder, position: Int) {
        val currentTime = hourEventArray[getItemViewType(position)]
        holder.itemView.apply {
            setHour(currentTime.time)
            setEvents(currentTime.events)
        }
    }

    /**
     *
     */
    override fun getItemViewType(position: Int): Int {
        return position
    }

    /**
     *
     */
    private fun setHour(time: LocalTime) {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        binding.tvHour.text = formatter.format(time)
        Log.d("Hour2", formatter.format(time))
    }

    /**
     *
     *
     */
    private fun setEvents(events: MutableList<Event>) {
        val event1 = binding.tvEvent1
        val event2 = binding.tvEvent2
        val event3 = binding.tvEvent3

        when (events.size) {
            0 -> {
                hideEvent(event1)
                hideEvent(event2)
                hideEvent(event3)
            }
            1 -> {
                setEvent(event1, events[0])
                hideEvent(event2)
                hideEvent(event3)
            }
            2 -> {
                setEvent(event1, events[0])
                setEvent(event2, events[1])
                hideEvent(event3)
            }
            3 -> {
                setEvent(event1, events[0])
                setEvent(event2, events[1])
                setEvent(event3, events[2])
            }
            else -> {
                setEvent(event1, events[0])
                setEvent(event2, events[1])
                event3.visibility = View.VISIBLE
                val eventsNotShown = String.format("%d", events.size - 2)
                val eventsNotShownText = "$eventsNotShown more events"
                event3.text = eventsNotShownText
            }
        }

    }

    /**
     *
     */
    private fun setEvent(tv: TextView, event: Event) {
        tv.text = event.name
        tv.visibility = View.VISIBLE
    }

    /**
     *
     */
    private fun hideEvent(tv: TextView) {
        tv.visibility = View.INVISIBLE
    }
}