package com.example.assignmentph.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val temperature: Double,
    val location: String,
    val windSpeed: Double,
    val timestamp: Long,
    val isFromNetwork: Boolean,
    val code : String,
    val humidity : Int
)
