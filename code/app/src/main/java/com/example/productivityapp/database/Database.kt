package com.example.productivityapp.database

import android.util.Log
import ca.antonious.materialdaypicker.MaterialDayPicker
import com.example.productivityapp.models.Habit
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot

class Database private constructor() {

    companion object {
        @Volatile //makes instance thread-safe with a private constructor
        private var INSTANCE: Database? = null

        fun getInstance(): Database? {
            if(INSTANCE == null) {
                synchronized(this) {
                    if(INSTANCE == null) {
                        INSTANCE = Database()
                    }
                }
            }
            return INSTANCE
        }
    }

    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun addHabitToDB(habit: Habit) {
        val habitToAdd = hashMapOf(
            "name" to habit.name,
            "description" to habit.description,
            "selectedDays" to habit.selectedDays,
            "repetitionInterval" to habit.repetitionInterval,
            "isComplete" to habit.isComplete,
            "timesCompleted" to habit.timesCompleted
        )

        db.collection("Habits").document(habit.name)
            .set(habitToAdd)
            .addOnCompleteListener { Log.d("DB", "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w("DB", "Error writing document", e) }
    }

    fun editHabit(name: String, habit: Habit) {
        db.collection("Habits").document(name).update(
            mapOf(
                "name" to habit.name,
                "description" to habit.description,
                "selectedDays" to habit.selectedDays,
                "repetitionInterval" to habit.repetitionInterval,
                "isComplete" to habit.isComplete,
                "timesCompleted" to habit.timesCompleted
            )
        )
    }

    fun getHabitsFromDB(callback: HabitListCallback) {
        val habits = mutableListOf<Habit>()

        db.collection("Habits").get().addOnCompleteListener {task ->
            if(task.isSuccessful) {
                for(doc: QueryDocumentSnapshot in task.result) {
                    val name = doc.getString("name")
                    val description = doc.getString("description")
                    val repetitionInterval = doc.getString("repetitionInterval")
                    val isComplete = doc.getBoolean("isComplete")
                    val timesCompleted = doc.getLong("timesCompleted")?.toInt()
                    val selectedDays = doc.get("selectedDays") as List<*>


                    val convertedDays = mutableListOf<MaterialDayPicker.Weekday>()
                    for(day in selectedDays) {
                        when (day) {
                            "MONDAY" -> {
                                convertedDays.add(MaterialDayPicker.Weekday.MONDAY)
                            }
                            "TUESDAY" -> {
                                convertedDays.add(MaterialDayPicker.Weekday.TUESDAY)
                            }
                            "WEDNESDAY" -> {
                                convertedDays.add(MaterialDayPicker.Weekday.WEDNESDAY)
                            }
                            "THURSDAY" -> {
                                convertedDays.add(MaterialDayPicker.Weekday.THURSDAY)
                            }
                            "FRIDAY" -> {
                                convertedDays.add(MaterialDayPicker.Weekday.FRIDAY)
                            }
                            "SATURDAY" -> {
                                convertedDays.add(MaterialDayPicker.Weekday.SATURDAY)
                            }
                            "SUNDAY" -> {
                                convertedDays.add(MaterialDayPicker.Weekday.SUNDAY)
                            }
                        }
                    }
                    val habit = Habit(name!!, description!!, convertedDays.toList(), repetitionInterval!!, isComplete!!, timesCompleted!!)
                    habits.add(habit)
                }
                callback.onGetHabits(habits)
            }
        }
    }

    fun deleteHabitFromDB(name: String) {
        db.collection("Habits").document(name).delete()
    }
}