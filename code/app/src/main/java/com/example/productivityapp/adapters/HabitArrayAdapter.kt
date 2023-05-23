package com.example.productivityapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.productivityapp.databinding.ItemHabitBinding
import com.example.productivityapp.fragments.AddHabitFragment
import com.example.productivityapp.fragments.EditHabitFragment
import com.example.productivityapp.models.Habit

class HabitArrayAdapter (private val habits: MutableList<Habit>):
    RecyclerView.Adapter<HabitArrayAdapter.HabitViewHolder>() {
    class HabitViewHolder(binding: ItemHabitBinding, clickAtPosition: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }
    }

    private lateinit var binding: ItemHabitBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        binding = ItemHabitBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val viewHolder = HabitViewHolder(binding) {
            var dialog = EditHabitFragment(this, it)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val currentHabit = habits[position]
        holder.itemView.apply {
            binding.tvHabitName.text = currentHabit.name
            binding.cbHabitCompleted.isChecked = currentHabit.isComplete

            binding.cbHabitCompleted.setOnCheckedChangeListener { _, _ ->
                currentHabit.isComplete = !currentHabit.isComplete
                Log.d("Test", currentHabit.isComplete.toString())
            }
        }

        holder.itemView.setOnClickListener {
            val dialog = EditHabitFragment(this, position)
            //Show EditHabitFragment
            dialog.show((it.context as FragmentActivity).supportFragmentManager, "Edit Habit")
            Log.d("Edit", "Done!")
        }
    }

    override fun getItemCount(): Int {
        return habits.size
    }

    fun getItem(position: Int): Habit {
        return habits[position]
    }

    fun addHabit(newHabit: Habit) {
        habits.add(newHabit)
        Log.d("New Habit:", newHabit.name)
        notifyItemInserted(habits.size - 1)
    }


}