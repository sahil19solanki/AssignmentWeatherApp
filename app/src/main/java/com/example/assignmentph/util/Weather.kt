package com.example.assignmentph.util

import androidx.annotation.DrawableRes
import com.example.assignmentph.R


sealed class WeatherType(
    val weatherDesc: String,
    @DrawableRes val iconRes: Int,
) {
    object ClearSkyd : WeatherType(
        weatherDesc = "Clear sky",
        iconRes = R.drawable.w01d2x
    )

    object ClearSkyn : WeatherType(
        weatherDesc = "Clear sky",
        iconRes = R.drawable.w01n2x
    )

    object FewCloudsd : WeatherType(
        weatherDesc = "few clouds",
        iconRes = R.drawable.w02d2x
    )

    object FewCloudsn : WeatherType(
        weatherDesc = "few clouds",
        iconRes = R.drawable.w02n2x
    )

    object ScatteredCloudsd : WeatherType(
        weatherDesc = "scattered clouds",
        iconRes = R.drawable.w03d2x
    )

    object ScatteredCloudsn : WeatherType(
        weatherDesc = "scattered clouds",
        iconRes = R.drawable.w03n2x
    )

    object BrokenCloudsd : WeatherType(
        weatherDesc = "broken clouds",
        iconRes = R.drawable.w04d2x
    )
    object BrokenCloudsn : WeatherType(
        weatherDesc = "broken clouds",
        iconRes = R.drawable.w04n2x
    )
    object ShowerRaind : WeatherType(
        weatherDesc = "shower rain",
        iconRes = R.drawable.w09d2x
    )
    object ShowerRainn : WeatherType(
        weatherDesc = "shower rain",
        iconRes = R.drawable.w09n2x
    )
    object Raind : WeatherType(
        weatherDesc = "rain",
        iconRes = R.drawable.w10d2x
    )
    object Rainn : WeatherType(
        weatherDesc = "rain",
        iconRes = R.drawable.w10n2x
    )
    object Thunderstormd : WeatherType(
        weatherDesc = "thunderstorm",
        iconRes = R.drawable.w11d2x
    )
    object Thunderstormn : WeatherType(
        weatherDesc = "thunderstorm",
        iconRes = R.drawable.w11n2x
    )
    object Snowd : WeatherType(
        weatherDesc = "snow",
        iconRes = R.drawable.w13d2x
    )
    object Snown : WeatherType(
        weatherDesc = "snow",
        iconRes = R.drawable.w13n2x
    )
    object Mistd : WeatherType(
        weatherDesc = "mist",
        iconRes = R.drawable.w50d2x
    )
    object Mistn : WeatherType(
        weatherDesc = "mist",
        iconRes = R.drawable.w50n2x
    )

    companion object {
        fun fromWMO(code: String): WeatherType {
            return when (code) {
                "01d" -> ClearSkyd
                "01n" -> ClearSkyn
                "02d" -> FewCloudsd
                "02n" -> FewCloudsn
                "03d" -> ScatteredCloudsd
                "03n" -> ScatteredCloudsn
                "04d" -> BrokenCloudsd
                "04n" -> BrokenCloudsn
                "09d" -> ShowerRaind
                "09n" -> ShowerRainn
                "10d" -> Raind
                "10n" -> Rainn
                "11d" -> Thunderstormd
                "11n" -> Thunderstormn
                "13d" -> Snowd
                "13n" -> Snown
                "50d" -> Mistd
                "50n" -> Mistn
                else -> ClearSkyd
            }
        }
    }
}