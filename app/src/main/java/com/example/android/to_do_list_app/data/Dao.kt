package com.example.android.to_do_list_app.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.android.to_do_list_app.model.Task

@Dao
interface Dao {

    @Insert
    suspend fun addTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM task_data")
    suspend fun deleteAllTask()

    @Query("SELECT * FROM task_data ORDER BY id ASC" )
    fun getAllData(): LiveData<List<Task>>

    @Query("SELECT * FROM task_data WHERE listItem LIKE :searchQuery ORDER BY id ASC")
    fun searchDatabase(searchQuery: String): LiveData<List<Task>>
}