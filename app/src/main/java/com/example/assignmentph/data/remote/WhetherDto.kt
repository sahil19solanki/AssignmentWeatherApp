package com.example.assignmentph.data.remote

import com.google.gson.annotations.SerializedName


data class WeatherResponseDTO(
    @SerializedName("coord") val coord: CoordDTO,
    @SerializedName("weather") val weather: List<WeatherDTO>,
    @SerializedName("base") val base: String,
    @SerializedName("main") val main: MainDTO,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("wind") val wind: WindDTO,
    @SerializedName("rain") val rain: RainDTO?,
    @SerializedName("clouds") val clouds: CloudsDTO,
    @SerializedName("dt") val dt: Long,
    @SerializedName("sys") val sys: SysDTO,
    @SerializedName("timezone") val timezone: Int,
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("cod") val cod: Int
)

data class CoordDTO(
    @SerializedName("lon") val lon: Double,
    @SerializedName("lat") val lat: Double
)

data class WeatherDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)

data class MainDTO(
    @SerializedName("temp") val temp: Double,
    @SerializedName("feels_like") val feels_like: Double,
    @SerializedName("temp_min") val temp_min: Double,
    @SerializedName("temp_max") val temp_max: Double,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("sea_level") val sea_level: Int,
    @SerializedName("grnd_level") val grnd_level: Int
)

data class WindDTO(
    @SerializedName("speed") val speed: Double,
    @SerializedName("deg") val deg: Int,
    @SerializedName("gust") val gust: Double
)

data class RainDTO(
    @SerializedName("1h") val oneHour: Double
)

data class CloudsDTO(
    @SerializedName("all") val all: Int
)

data class SysDTO(
    @SerializedName("type") val type: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("country") val country: String,
    @SerializedName("sunrise") val sunrise: Long,
    @SerializedName("sunset") val sunset: Long
)
