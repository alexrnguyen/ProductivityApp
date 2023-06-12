package com.example.productivityapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.productivityapp.interfaces.DismissCallback
import com.example.productivityapp.R
import com.example.productivityapp.databinding.ItemHabitBinding
import com.example.productivityapp.fragments.HabitFragment
import com.example.productivityapp.models.Habit

class HabitArrayAdapter (private val habits: MutableList<Habit>):
    RecyclerView.Adapter<HabitArrayAdapter.HabitViewHolder>() {
    class HabitViewHolder(binding: ItemHabitBinding) : RecyclerView.ViewHolder(binding.root) {

    }

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

            binding.cbHabitCompleted.setOnCheckedChangeListener { _, _ ->
                currentHabit.isComplete = !currentHabit.isComplete
                Log.d("Test", currentHabit.isComplete.toString())
            }
        }

        holder.itemView.setOnClickListener {
            //val dialog = EditHabitFragment(this, position)
            val fragment = HabitFragment(this, position, object : DismissCallback {
                override fun onCallback(newHabit: Habit) {
                    var currentPosition = holder.adapterPosition
                    editHabit(newHabit, currentPosition)
                }
            })
            val container = it?.parent as? ViewGroup
            (it.context as FragmentActivity).supportFragmentManager.beginTransaction().replace(R.id.frame_layout,fragment).addToBackStack(null).commit()
            //Show EditHabitFragment
            //dialog.show((it.context as FragmentActivity).supportFragmentManager, "View Habit")
        }
    }

    /**
     * Get the number of items in the habits list
     * @return Returns the number of items in the habits list
     */
    override fun getItemCount(): Int {
        return habits.size
    }

    /**
     * Get a habit (item) at a specific position in the habits list
     * @param position The position in the list to retrieve a habit from
     * @return Returns the habit at the specified position in the habits list
     */
    fun getItem(position: Int): Habit {
        return habits[position]
    }

    /**
     * Add a habit to the habits list
     * @param newHabit The habit to add to the habits list
     */
    fun addHabit(newHabit: Habit) {
        habits.add(newHabit)
        Log.d("New Habit:", newHabit.name)
        notifyItemInserted(habits.size - 1)
    }

    /**
     * Change a habit's attributes and update the habits list
     * @param habit The habit to change in the list
     * @param position The position of the habit to change in the list
     */
    fun editHabit(habit: Habit, position: Int) {
        habits[position] = habit
        notifyItemChanged(position)
    }

    fun removeAt(position: Int) {
        habits.removeAt(position)
        notifyItemRemoved(position)
    }

}