package com.ahmadmukhlish.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.ahmadmukhlish.todolist.db.Repository
import com.ahmadmukhlish.todolist.db.ToDo
import kotlinx.coroutines.runBlocking

class MAViewModel(private val repository: Repository) : ViewModel() {
    val items = repository.getList().asLiveData()

    fun addItem(item: ToDo) = runBlocking{repository.addItem(item)}

    fun deleteItem(item: String) = runBlocking { repository.deleteItem(item) }
}

class MAViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MAViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return MAViewModel(repository) as T
        }
        else throw IllegalArgumentException("Unknown view model class")
    }

}