package com.example.assignmentph.util

import android.content.Context
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.List

object Utils {
    val API_KEY = "f27116535e8bdcf9ac548e88971b1200"
    fun formatTemperature(kelvinTemperature: Double): String {
        // Convert Kelvin to Celsius and format the result to 2 decimal places
        val celsiusTemperature = kelvinTemperature - 273.15
        return String.format(Locale.getDefault(), "%.2f", celsiusTemperature)
    }
    fun getWeatherDesc(code: String): String {
        val weatherType = WeatherType.fromWMO(code)
        return weatherType.weatherDesc
    }
    fun formatTimestamp(timestamp: Long): String {
        // Convert timestamp to human-readable date and time
        val dateFormat = SimpleDateFormat("MMM dd, yyyy - hh:mm a", Locale.getDefault())
        return dateFormat.format(Date(timestamp))
    }
    fun getWeatherIcon(code: String): Int {
        val weatherType = WeatherType.fromWMO(code)
        return weatherType.iconRes
    }

    fun getLocations(): List<android.location.Location> {
        return listOf(
            android.location.Location("New York").apply { latitude = 40.7128; longitude = -74.0060 },
            android.location.Location("Singapore").apply { latitude = 1.3521; longitude = 103.8198 },
            android.location.Location("Mumbai").apply { latitude = 19.0760; longitude = 72.8777 },
            android.location.Location("Delhi").apply { latitude = 28.6139; longitude = 77.2090 },
            android.location.Location("Sydney").apply { latitude = -33.8688; longitude = 151.2093 },
            android.location.Location("Melbourne").apply { latitude = -37.8136; longitude = 144.9631 }
        )
    }


}

object NetworkUtils {
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            return capabilities != null &&
                    (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }
}