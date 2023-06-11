package com.example.productivityapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.productivityapp.DismissCallback
import com.example.productivityapp.DismissTodoCallback
import com.example.productivityapp.R
import com.example.productivityapp.adapters.TodoItemArrayAdapter
import com.example.productivityapp.database.Database
import com.example.productivityapp.databinding.FragmentTodoListBinding
import com.example.productivityapp.models.TodoItem


/**
 * A simple [Fragment] subclass.
 * Use the [TodoList.newInstance] factory method to
 * create an instance of this fragment.
 */
class TodoList : Fragment() {

    private lateinit var binding: FragmentTodoListBinding
    private lateinit var todoItemArrayAdapter: TodoItemArrayAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodoListBinding.inflate(layoutInflater)
        todoItemArrayAdapter = TodoItemArrayAdapter(mutableListOf())
        binding.rvTodoList.adapter = todoItemArrayAdapter
        binding.rvTodoList.layoutManager = LinearLayoutManager(context)

        noItemsTextVisibility()

        binding.fabAddButton.setOnClickListener {
            //Referenced: https://stackoverflow.com/questions/61948788/how-do-i-open-fragment-from-fragment-kotlin
            val dialog = AddTodoItemFragment(todoItemArrayAdapter, object : DismissTodoCallback {
                override fun onCallback() {
                    noItemsTextVisibility()
                }
            })
            activity?.let { it1 -> dialog.show(it1.supportFragmentManager, "Add To Do Item") }
        }

        setRecyclerViewItemTouchListener()
        return binding.root
    }


    /**
     *
     */
    private fun setRecyclerViewItemTouchListener() {
        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
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
                todoItemArrayAdapter.removeAt(position)
                val message = Toast.makeText(requireContext(), "Item Deleted!", Toast.LENGTH_LONG)
                message.show()
            }

            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = 0
                return makeMovementFlags(dragFlags, swipeFlags)
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvTodoList)
    }

    /**
     *
     */
    private fun noItemsTextVisibility() {
        if(todoItemArrayAdapter.itemCount == 0) {
            binding.tvNoItems.visibility = View.VISIBLE
        }
        else {
            binding.tvNoItems.visibility = View.INVISIBLE
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TodoList.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            TodoList().apply {

            }
    }
}