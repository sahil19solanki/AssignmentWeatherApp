package com.example.assignmentph.data.repository

import com.example.assignmentph.data.remote.WeatherApi
import com.example.assignmentph.data.remote.WeatherResponseDTO
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherRepository {

    override suspend fun getWeatherData(latitude: Double, longitude: Double, apiKey: String): Response<WeatherResponseDTO> {
        return weatherApi.getWeatherData(latitude, longitude, apiKey)
    }
}
