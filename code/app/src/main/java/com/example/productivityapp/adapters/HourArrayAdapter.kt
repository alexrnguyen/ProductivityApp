package com.example.productivityapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.productivityapp.EventList
import com.example.productivityapp.databinding.ItemHourCellBinding
import com.example.productivityapp.models.Event
import com.example.productivityapp.models.HourEvent
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class HourArrayAdapter(private val hourEventArray: MutableList<HourEvent>): RecyclerView.Adapter<HourArrayAdapter.HourEventViewHolder>() {
    class HourEventViewHolder(binding: ItemHourCellBinding) : RecyclerView.ViewHolder(binding.root) {
        var hour = binding.tvHour
        var events = binding.rvEventList
    }

    private var viewPool = RecyclerView.RecycledViewPool()
    private lateinit var binding: ItemHourCellBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourEventViewHolder {
        binding = ItemHourCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return HourEventViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return hourEventArray.size
    }

    override fun onBindViewHolder(holder: HourEventViewHolder, position: Int) {
        val currentTime = hourEventArray[position]
        holder.itemView.apply {
            setHour(currentTime.time)
            //setEvents(currentTime.events)
            var layoutManager = LinearLayoutManager(holder.events.context, LinearLayoutManager.VERTICAL, false)
            layoutManager.initialPrefetchItemCount = currentTime.events.size

            var eventArrayAdapter = EventArrayAdapter(currentTime.events)
            binding.rvEventList.layoutManager = layoutManager
            binding.rvEventList.adapter = eventArrayAdapter
            binding.rvEventList.setRecycledViewPool(viewPool)
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
    fun addEventToEvents(event: Event, hour: Int) {
        hourEventArray[hour].events.add(event)
        Log.d("Event Added: ", hourEventArray[hour].events[0].toString())
        notifyItemChanged(hour)
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
    private fun setEvents(events: EventList) {
        /*val event1 = binding.tvEvent1
        val event2 = binding.tvEvent2
        val event3 = binding.tvEvent3

        when (events.size()) {
            0 -> {
                hideEvent(event1)
                hideEvent(event2)
                hideEvent(event3)
            }
            1 -> {
                setEvent(event1, events.get(0))
                hideEvent(event2)
                hideEvent(event3)
            }
            2 -> {
                setEvent(event1, events.get(0))
                setEvent(event2, events.get(1))
                hideEvent(event3)
            }
            3 -> {
                setEvent(event1, events.get(0))
                setEvent(event2, events.get(1))
                setEvent(event3, events.get(2))
            }
            else -> {
                setEvent(event1, events.get(0))
                setEvent(event2, events.get(1))
                event3.visibility = View.VISIBLE
                val eventsNotShown = String.format("%d", events.size() - 2)
                val eventsNotShownText = "$eventsNotShown more events"
                event3.text = eventsNotShownText
            }
        }*/

        //var layoutManager = LinearLayoutManager()

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