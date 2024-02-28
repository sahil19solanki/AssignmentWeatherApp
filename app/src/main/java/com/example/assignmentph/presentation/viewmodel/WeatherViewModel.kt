package com.example.assignmentph.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignmentph.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    fun getWeatherData(latitude: Double, longitude: Double, apiKey: String) {
        viewModelScope.launch {
            try {
                val response= weatherRepository.getWeatherData(latitude, longitude, apiKey)
                if (response.isSuccessful) {
                    // Handle successful response
                    val data = response.body()
                    // Process data
                    Log.d("Weather call is done",data.toString())
                } else {
                    // Handle error response
                    Log.d("Weather call is done","Failed")

                }
            } catch (e: Exception) {
                // Handle exception
                e.printStackTrace()
                Log.d("Weather call is done",e.message.toString())

            }
        }
    }

}