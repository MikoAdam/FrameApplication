package com.frame_application.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frame_application.application.Application
import com.frame_application.data.Repository
import com.frame_application.model.Item
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {

    private val repository: Repository

    val allItems: LiveData<List<Item>>

    init {
        val databaseDao = Application.database.dao()
        repository = Repository(databaseDao)
        allItems = repository.getAllItems()
    }

    fun insert(item: Item) = viewModelScope.launch {
        repository.insert(item)
    }

    fun delete(item: Item) = viewModelScope.launch {
        repository.delete(item)
    }

    fun update(item: Item) = viewModelScope.launch {
        repository.update(item)
    }

    fun deleteAll() = viewModelScope.launch { repository.deleteAll() }
}