package com.example.assignmentph.data.repository

import com.example.assignmentph.data.remote.WeatherResponseDTO
import retrofit2.Response


interface WeatherRepository {
    suspend fun getWeatherData(latitude: Double, longitude: Double, apiKey: String): Response<WeatherResponseDTO>
}