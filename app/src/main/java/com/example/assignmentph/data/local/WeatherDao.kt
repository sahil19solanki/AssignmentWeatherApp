package com.example.assignmentph.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
//
//@Dao
//interface WeatherDao {
//    @Query("SELECT * FROM weather_data WHERE name = :cityName")
//    suspend fun getWeatherData(cityName: String): WeatherDataEntity?
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertWeatherData(weatherDataEntity: WeatherDataEntity)
//}