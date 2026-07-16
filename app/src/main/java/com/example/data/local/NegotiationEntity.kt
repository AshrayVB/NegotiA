package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "negotiations")
data class NegotiationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val type: String,
    val initialAmount: Double = 0.0,
    val targetAmount: Double = 0.0,
    val analysisResult: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
