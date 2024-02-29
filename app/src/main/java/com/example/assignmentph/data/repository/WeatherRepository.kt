package com.example.assignmentph.data.repository

import com.example.assignmentph.data.local.WeatherEntity
import com.example.assignmentph.data.remote.WeatherResponseDTO
import retrofit2.Response


interface WeatherRepository {
    suspend fun getLocalWeatherData(): List<WeatherEntity>
    suspend fun getLatestNetworkWeatherData(): WeatherEntity?
    suspend fun refreshWeatherData(latitude: Double, longitude: Double, apiKey: String): WeatherEntity?

    suspend fun getWeatherData(latitude: Double, longitude: Double, apiKey: String): Response<WeatherResponseDTO>
}