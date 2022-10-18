package com.example.android.to_do_list_app.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "task_data")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val listItem: String,
    var isChecked: Boolean

): Parcelable