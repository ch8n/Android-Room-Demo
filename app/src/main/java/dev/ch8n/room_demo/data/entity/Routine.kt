package dev.ch8n.room_demo.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Routine(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val description: String,
    val startTime: Date,
    val endTime: Date,
    val day: Day
)