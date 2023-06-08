package com.example.productivityapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import com.example.productivityapp.databinding.ActivityMainBinding
import com.example.productivityapp.fragments.Calendar
import com.example.productivityapp.fragments.HabitTracker
import com.example.productivityapp.fragments.TodoList

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //Set initial fragment to To-do List fragment
        replaceFragment(TodoList())
        binding.bottomNavigationView.selectedItemId = R.id.todo

        //Replace fragment when the user selects a new fragment
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.calendar -> replaceFragment(Calendar())
                R.id.todo -> replaceFragment(TodoList())
                R.id.habits -> replaceFragment(HabitTracker())
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.action_bar, menu)
        val item = menu?.findItem(R.id.switch_light_dark_menu)
        item?.setActionView(R.layout.switch_light_dark)

        item?.actionView?.findViewById<SwitchCompat>(R.id.switch_light_dark_mode)
            ?.setOnCheckedChangeListener { _, isChecked ->
                Log.d("Switch", isChecked.toString())
                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        return true
    }

    /**
     *
     * @param fragment
     */
    private fun replaceFragment(fragment : Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}