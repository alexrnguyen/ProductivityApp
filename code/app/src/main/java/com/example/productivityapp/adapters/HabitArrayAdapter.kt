package com.example.productivityapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.productivityapp.databinding.ItemHabitBinding
import com.example.productivityapp.fragments.AddHabitFragment
import com.example.productivityapp.models.Habit

class HabitArrayAdapter (private val habits: MutableList<Habit>):
    RecyclerView.Adapter<HabitArrayAdapter.HabitViewHolder>() {
    class HabitViewHolder(binding: ItemHabitBinding) : RecyclerView.ViewHolder(binding.root)

    private lateinit var binding: ItemHabitBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        binding = ItemHabitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val currentHabit = habits[position]
        holder.itemView.apply {
            binding.tvHabitName.text = currentHabit.name
            binding.cbHabitCompleted.isChecked = currentHabit.isComplete

            binding.tvHabitName.setOnClickListener {

            }

            binding.cbHabitCompleted.setOnCheckedChangeListener { _, _ ->
                currentHabit.isComplete = !currentHabit.isComplete
            }
        }
    }

    override fun getItemCount(): Int {
        return habits.size
    }

    fun addHabit(newHabit: Habit) {
        habits.add(newHabit)
        Log.d("New Habit:", newHabit.name)
        notifyItemInserted(habits.size - 1)
    }

}