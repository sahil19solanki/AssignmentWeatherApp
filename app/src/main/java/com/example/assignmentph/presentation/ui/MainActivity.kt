package com.example.assignmentph.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels  // Import viewModels from androidx.activity
import com.example.assignmentph.R
import com.example.assignmentph.presentation.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val weatherViewModel: WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dummyLatitude = 37.7749
        val dummyLongitude = -122.4194
        val dummyApiKey = "f27116535e8bdcf9ac548e88971b1200"

        weatherViewModel.getWeatherData(dummyLatitude, dummyLongitude, dummyApiKey)

    }
}