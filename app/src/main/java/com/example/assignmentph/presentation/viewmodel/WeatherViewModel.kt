package com.example.assignmentph.presentation.viewmodel

import android.health.connect.datatypes.ExerciseRoute
import android.location.Location
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignmentph.data.local.WeatherEntity
import com.example.assignmentph.data.remote.toWeatherEntity
import com.example.assignmentph.data.repository.WeatherRepository
import com.example.assignmentph.util.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _localWeatherData = MutableLiveData<List<WeatherEntity>>()
    val localWeatherData: LiveData<List<WeatherEntity>> get() = _localWeatherData

    private val _latestNetworkWeatherData = MutableLiveData<WeatherEntity?>()
    val latestNetworkWeatherData: LiveData<WeatherEntity?> get() = _latestNetworkWeatherData


    private val _weatherData = MutableLiveData<List<WeatherEntity>>()
    val weatherData: LiveData<List<WeatherEntity>> get() = _weatherData
    fun getLocalWeatherData() {
        viewModelScope.launch {
            _localWeatherData.value = weatherRepository.getLocalWeatherData()
        }
    }

    fun getLatestNetworkWeatherData() {
        viewModelScope.launch {
            _latestNetworkWeatherData.value = weatherRepository.getLatestNetworkWeatherData()
        }
    }

    fun refreshWeatherData(latitude: Double, longitude: Double, apiKey: String) {
        viewModelScope.launch {
            _latestNetworkWeatherData.value = weatherRepository.refreshWeatherData(latitude, longitude, apiKey)
            getLocalWeatherData()
        }
    }


    fun getWeatherForLocations(locations: List<Location>) {
        viewModelScope.launch {
            val apiKey = Utils.API_KEY
            val weatherEntities = mutableListOf<WeatherEntity>()

            for (location in locations) {
                val response = weatherRepository.getWeatherData(
                    location.latitude,
                    location.longitude,
                    apiKey
                )

                if (response.isSuccessful) {
                    val weatherData = response.body()
                    weatherData?.let {
                        val timestamp = System.currentTimeMillis()
                        val weatherEntity = it.toWeatherEntity(timestamp, false)
                        weatherEntities.add(weatherEntity)
                    }
                }
            }

            // Update LiveData with the result
            _weatherData.value = weatherEntities
        }
    }
}
