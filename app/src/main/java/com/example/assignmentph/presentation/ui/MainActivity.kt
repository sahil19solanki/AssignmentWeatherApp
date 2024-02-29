package com.example.assignmentph.presentation.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.health.connect.datatypes.ExerciseRoute
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels  // Import viewModels from androidx.activity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.assignmentph.R
import com.example.assignmentph.data.local.WeatherEntity
import com.example.assignmentph.databinding.ActivityMainBinding
import com.example.assignmentph.presentation.viewmodel.WeatherViewModel
import com.example.assignmentph.util.WeatherType
import android.location.Location
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentph.presentation.ui.adapter.WeatherAdapter
import com.example.assignmentph.util.NetworkUtils
import com.example.assignmentph.util.Utils
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val weatherViewModel: WeatherViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    private lateinit var weatherAdapter: WeatherAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObservers()

        requestLocationPermission()

        weatherAdapter = WeatherAdapter(emptyList())

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity,2)
            adapter = weatherAdapter
        }

        if (NetworkUtils.isNetworkAvailable(this)) {
            weatherViewModel.getWeatherForLocations(Utils.getLocations())
            binding.textNoInternet.visibility = View.GONE
            binding.recyclerView.visibility =View.VISIBLE
        }
        else {
            Snackbar.make(
                binding.recyclerView,
                "No internet connection. Showing cached data.",
                Snackbar.LENGTH_LONG
            ).show()
            binding.textNoInternet.visibility = View.VISIBLE
            binding.recyclerView.visibility =View.GONE
        }


    }



    private fun initObservers() {
        weatherViewModel.localWeatherData.observe(this, Observer { localWeatherData ->
            localWeatherData.forEach { weatherEntity ->
                updateUi(weatherEntity)
            }
        })

        weatherViewModel.latestNetworkWeatherData.observe(this, Observer { latestNetworkWeatherData ->
            latestNetworkWeatherData?.let {
                updateUi(latestNetworkWeatherData)
            }
        })
        weatherViewModel.weatherData.observe(this){
            weatherAdapter.updateData(it)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateUi(weatherEntity: WeatherEntity) {
        binding.textLocation.text = weatherEntity.location
        binding.textTemperature.text = "${Utils.formatTemperature(weatherEntity.temperature)}\u00B0C"
        binding.textWind.text = "${weatherEntity.windSpeed} m/s"
        binding.textTimestamp.text = Utils.formatTimestamp(weatherEntity.timestamp)
        binding.imageIcon.setImageResource(Utils.getWeatherIcon(weatherEntity.code))
        binding.humidity.text = weatherEntity.humidity.toString()+" %"
        binding.textDesc.text = Utils.getWeatherDesc(weatherEntity.code)
    }


    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                getLocationAndWeather()
            } else {
                Snackbar.make(
                    binding.root,
                    "Location permission denied. Weather data cannot be fetched.",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

    private fun requestLocationPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                getLocationAndWeather()
            }
            else -> {
                requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    private fun getLocationAndWeather() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestLocationPermission()
        } else {
            fusedLocationClient.lastLocation
                .addOnSuccessListener {
                    it?.let {
                        val apiKey = Utils.API_KEY
                        weatherViewModel.refreshWeatherData(it.latitude, it.longitude, apiKey)
                    }
                }
                .addOnFailureListener { exception ->
                    Snackbar.make(
                        binding.root,
                        "Failed to get location detail",
                        Snackbar.LENGTH_LONG
                    ).show()
                    Log.d("exception with permission",exception.toString())
                    exception.printStackTrace()
                }
        }
    }



}



