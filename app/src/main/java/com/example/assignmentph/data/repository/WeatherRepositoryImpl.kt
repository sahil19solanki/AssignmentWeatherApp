package com.example.assignmentph.data.repository

import android.util.Log
import com.example.assignmentph.data.local.WeatherDatabase
import com.example.assignmentph.data.local.WeatherEntity
import com.example.assignmentph.data.remote.WeatherApi
import com.example.assignmentph.data.remote.WeatherResponseDTO
import com.example.assignmentph.data.remote.toWeatherEntity
import retrofit2.Response
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val weatherDatabase: WeatherDatabase
) : WeatherRepository {

    private val weatherDao = weatherDatabase.weatherDao()

    override suspend fun getLocalWeatherData(): List<WeatherEntity> {
        return weatherDao.getSavedWeatherData()
    }

    override suspend fun getLatestNetworkWeatherData(): WeatherEntity? {
        return weatherDao.getLatestNetworkWeatherData()
    }

    override suspend fun refreshWeatherData(latitude: Double, longitude: Double, apiKey: String): WeatherEntity? {
        try {
            val networkResponse = weatherApi.getWeatherData(latitude, longitude, apiKey)
            if (networkResponse.isSuccessful) {
                val networkWeatherData = networkResponse.body()

                weatherDao.clearOldData()

                networkWeatherData?.let {
                    val timestamp = System.currentTimeMillis()
                    val weatherEntity = it.toWeatherEntity(timestamp, true)
                    weatherDao.insert(weatherEntity)
                }

                return networkWeatherData?.toWeatherEntity(System.currentTimeMillis(), true)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Network error: ${e.message}")
        }
        return weatherDao.getLatestNetworkWeatherData()
    }

    override suspend fun getWeatherData(latitude: Double, longitude: Double, apiKey: String): Response<WeatherResponseDTO> {
        return weatherApi.getWeatherData(latitude, longitude, apiKey)
    }
    companion object {
        private const val TAG = "WeatherRepositoryImpl"
    }
}
