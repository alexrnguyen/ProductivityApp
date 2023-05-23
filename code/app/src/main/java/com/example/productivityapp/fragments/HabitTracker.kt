package com.example.productivityapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productivityapp.R
import com.example.productivityapp.adapters.HabitArrayAdapter
import com.example.productivityapp.databinding.FragmentHabitTrackerBinding

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
        habitArrayAdapter = HabitArrayAdapter(mutableListOf())

        binding.rvHabitList.adapter = habitArrayAdapter
        binding.rvHabitList.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Log.d("FAB", "Success!")
        //Add a habit to habit list when add button is clicked
        binding.fabAddButton.setOnClickListener {
            //Referenced: https://stackoverflow.com/questions/61948788/how-do-i-open-fragment-from-fragment-kotlin
            Log.d("FAB", "Add Clicked!")
            val dialog = AddHabitFragment(habitArrayAdapter)

            activity?.let { it1 -> dialog.show(it1.supportFragmentManager, "Add Habit") }
        }

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