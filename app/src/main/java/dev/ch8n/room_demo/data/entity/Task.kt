package dev.ch8n.room_demo.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val title: String,
    val description: String,
    val priority: Int,
    val day: Int
)