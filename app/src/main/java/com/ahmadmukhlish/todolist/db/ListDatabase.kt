package com.ahmadmukhlish.todolist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ToDo::class], version = 1, exportSchema = false)
abstract class ListDatabase : RoomDatabase(){
    abstract fun Dao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: ListDatabase? = null

        fun getDatabase(context: Context): ListDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ListDatabase::class.java,
                        "list_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}