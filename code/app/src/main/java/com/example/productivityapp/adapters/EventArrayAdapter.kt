package com.example.productivityapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.productivityapp.databinding.ItemEventBinding
import com.example.productivityapp.models.Event

class EventArrayAdapter (private val events: MutableList<Event>):
    RecyclerView.Adapter<EventArrayAdapter.EventViewHolder>() {

    private lateinit var binding: ItemEventBinding
    class EventViewHolder(binding: ItemEventBinding) : RecyclerView.ViewHolder(binding.root) {
        var name = binding.tvEventName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return EventViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val currentEvent = events[position]
        holder.itemView.apply {
            holder.name.text = currentEvent.name
        }
    }


    fun addEvent(event: Event) {
        events.add(event)
        notifyItemInserted(itemCount-1)
    }
}