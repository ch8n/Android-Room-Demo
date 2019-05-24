package dev.ch8n.room_demo.data.entity

import androidx.room.Entity

@Entity
data class Day(val routines: ArrayList<Routine>)