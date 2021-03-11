package com.ahmadmukhlish.todolist.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDo(
        @PrimaryKey
        val content: String
)