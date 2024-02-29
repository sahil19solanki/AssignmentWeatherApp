package com.example.assignmentph.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weather: WeatherEntity)

    @Query("SELECT * FROM weather_table WHERE isFromNetwork = 0 ORDER BY timestamp DESC")
    suspend fun getSavedWeatherData(): List<WeatherEntity>

    @Query("SELECT * FROM weather_table WHERE isFromNetwork = 1 ORDER BY timestamp DESC LIMIT 1")
    suspend fun getLatestNetworkWeatherData(): WeatherEntity?

    @Query("DELETE FROM weather_table")
    suspend fun clearOldData()
}