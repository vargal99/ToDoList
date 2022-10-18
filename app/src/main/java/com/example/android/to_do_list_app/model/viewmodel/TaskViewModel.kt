package com.example.android.to_do_list_app.model.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.android.to_do_list_app.model.Task
import com.example.android.to_do_list_app.data.TaskDatabase
import com.example.android.to_do_list_app.model.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application): AndroidViewModel(application) {


    private val repository: TaskRepository
    private val taskDao = TaskDatabase.getDatabase(application).taskDAo()

    val getAllData: LiveData<List<Task>>

    init {
        repository = TaskRepository(taskDao)
        getAllData = repository.getAllData()
    }

    fun addTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(task)
        }
    }

    fun updateTask(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateTask(task)
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteTask(task)
        }
    }


    fun deleteAllTask(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllTask()
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Task>> {
        return repository.searchDatabase(searchQuery)
    }
}