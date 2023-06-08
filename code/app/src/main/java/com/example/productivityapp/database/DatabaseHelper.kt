package com.example.productivityapp.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.productivityapp.models.Habit

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

        companion object {
            private const val DATABASE_VERSION = 1
            private const val DATABASE_NAME = "HabitDatabase"
            private const val TABLE_HABITS = "HabitTable"

            private const val COLUMN_NAME = "COLUMN_NAME"
            private const val COLUMN_DESCRIPTION = "COLUMN_DESCRIPTION"
            private const val COLUMN_SELECTED_DAYS = "COLUMN_SELECTED_DAYS"
            private const val COLUMN_REPETITION_INTERVAL = "COLUMN_REPETITION_INTERVAL"
            private const val COLUMN_IS_COMPLETE = "COLUMN_IS_COMPLETE"
            private const val COLUMN_TIMES_COMPLETED = "COLUMN_TIMES_COMPLETED"
        }

    /**
     * Called the first time the database is accessed.
     */
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableStatement = "CREATE TABLE $TABLE_HABITS " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_NAME TEXT, " +
                "$COLUMN_DESCRIPTION TEXT, " +
                "$COLUMN_SELECTED_DAYS LIST, " +
                "$COLUMN_REPETITION_INTERVAL TEXT, " +
                "$COLUMN_IS_COMPLETE BOOL, " +
                "$COLUMN_TIMES_COMPLETED INT)"
        db?.execSQL(createTableStatement)
    }

    /**
     * Called when the database version changes. Prevents app from crashing when the database
     * design changes
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun addData(habit: Habit): Boolean {
        var db = this.writableDatabase
        var contentValues = ContentValues()

        contentValues.put(COLUMN_NAME, habit.name)
        contentValues.put(COLUMN_DESCRIPTION, habit.description)
        //contentValues.
        contentValues.put(COLUMN_REPETITION_INTERVAL, habit.repetitionInterval)
        contentValues.put(COLUMN_IS_COMPLETE, habit.isComplete)
        contentValues.put(COLUMN_TIMES_COMPLETED, habit.timesCompleted)

        val insert = db.insert(TABLE_HABITS, null, contentValues)

        //Insert = 1 if successful; Insert = -1 if the insertion failed
        return insert != -1L
    }


}