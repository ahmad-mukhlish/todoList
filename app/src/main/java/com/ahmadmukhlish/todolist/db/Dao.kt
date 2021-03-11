package com.ahmadmukhlish.todolist.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(todo: ToDo)

    @Query("SELECT * FROM ToDo")
    fun getList(): Flow<List<ToDo>>

    @Query("DELETE FROM ToDo WHERE content = :content")
    suspend fun deleteItem(content: String)
}