package com.example.productivityapp.fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.productivityapp.R
import com.example.productivityapp.adapters.HabitArrayAdapter
import com.example.productivityapp.databinding.FragmentAddHabitBinding
import com.example.productivityapp.databinding.FragmentEditHabitBinding
import com.example.productivityapp.models.Habit

/**
 * A simple [Fragment] subclass.
 * Use the [AddHabitFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditHabitFragment(private var habitArrayAdapter: HabitArrayAdapter) : DialogFragment() {

    private lateinit var binding: FragmentEditHabitBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //var rootView: View = inflater.inflate(R.layout.fragment_add_habit, container, false)
        binding = FragmentEditHabitBinding.inflate(layoutInflater)

        binding.btnSaveHabit.setOnClickListener {
            dismiss()
        }

        binding.btnCancelAddHabit.setOnClickListener {
            dismiss()
        }
        return binding.root
    }
}