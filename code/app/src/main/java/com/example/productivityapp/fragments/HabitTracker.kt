package com.example.productivityapp.fragments

import android.app.Notification
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.productivityapp.R
import com.example.productivityapp.adapters.HabitArrayAdapter
import com.example.productivityapp.interfaces.HabitListCallback
import com.example.productivityapp.database.Database
import com.example.productivityapp.databinding.FragmentHabitTrackerBinding
import com.example.productivityapp.models.Habit

/**
 * A simple [Fragment] subclass.
 * Use the [HabitTracker.newInstance] factory method to
 * create an instance of this fragment.
 */
class HabitTracker : Fragment() {

    private lateinit var binding: FragmentHabitTrackerBinding

    private lateinit var habitArrayAdapter: HabitArrayAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHabitTrackerBinding.inflate(layoutInflater)

        // TODO: Need to retrieve the list of habits from a database to maintain persistent data
        val db = Database.getInstance()
        db?.getHabitsFromDB(object : HabitListCallback {
            override fun onGetHabits(habits: MutableList<Habit>) {
                Log.d("Habits", habits.toString())
                habitArrayAdapter = HabitArrayAdapter(habits)
                binding.rvHabitList.adapter = habitArrayAdapter
                binding.rvHabitList.layoutManager = LinearLayoutManager(context)

                binding.fabAddButton.setOnClickListener {
                    //Referenced: https://stackoverflow.com/questions/61948788/how-do-i-open-fragment-from-fragment-kotlin
                    val dialog = AddHabitFragment(habitArrayAdapter)

                    activity?.let { it1 -> dialog.show(it1.supportFragmentManager, "Add Habit") }
                }
            }
        })

        setRecyclerViewItemTouchListener()
        return binding.root
    }

    /**
     * Delete a habit from the habits list when a user swipes
     */
    private fun setRecyclerViewItemTouchListener() {
        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                recyclerView.adapter?.notifyItemMoved(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val db = Database.getInstance()
                db?.deleteHabitFromDB(habitArrayAdapter.getItem(position).name)
                habitArrayAdapter.removeAt(position)
                val message = Toast.makeText(requireContext(), "Habit Deleted!", Toast.LENGTH_LONG)
                message.show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvHabitList)
    }

    fun remindUser(): Notification {
        val builder = NotificationCompat.Builder(this.requireContext(), "Habit")
            .setSmallIcon(R.drawable.baseline_check_24)
            .setContentTitle("Reminder")
            .setContentText("Test")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
        return builder.build()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment HabitTracker.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HabitTracker().apply {

            }
    }
}