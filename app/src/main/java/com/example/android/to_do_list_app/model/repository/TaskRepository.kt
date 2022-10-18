package com.example.android.to_do_list_app.model.repository

import androidx.lifecycle.LiveData
import com.example.android.to_do_list_app.data.Dao
import com.example.android.to_do_list_app.model.Task

class TaskRepository(private val taskDao: Dao) {

    fun getAllData(): LiveData<List<Task>> = taskDao.getAllData()

    suspend fun addTask(task: Task) {
        taskDao.addTask(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    suspend fun deleteAllTask() {
        taskDao.deleteAllTask()
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Task>> {
        return taskDao.searchDatabase(searchQuery)
    }
}