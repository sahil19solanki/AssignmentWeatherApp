package com.example.assignmentph.presentation.ui.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentph.R
import com.example.assignmentph.data.local.WeatherEntity
import com.example.assignmentph.util.WeatherType
import java.util.*

class WeatherAdapter(private var weatherList: List<WeatherEntity>) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    fun updateData(newWeatherList: List<WeatherEntity>) {
        weatherList = newWeatherList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weather = weatherList[position]
        holder.bind(weather)
    }

    override fun getItemCount(): Int = weatherList.size

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textCity: TextView = itemView.findViewById(R.id.textCity)
        private val textTemperature: TextView = itemView.findViewById(R.id.textTemperature)
        private val textDescription: TextView = itemView.findViewById(R.id.textDescription)

        @SuppressLint("SetTextI18n")
        fun bind(weather: WeatherEntity) {
            textCity.text = weather.location
            textTemperature.text = "${formatTemperature(weather.temperature)}\u00B0C"
            textDescription.text = getWeatherDesc(weather.code)
        }
        fun getWeatherDesc(code: String): String {
            val weatherType = WeatherType.fromWMO(code)
            return weatherType.weatherDesc
        }
        private fun formatTemperature(kelvinTemperature: Double): String {
            val celsiusTemperature = kelvinTemperature - 273.15
            return String.format(Locale.getDefault(), "%.2f", celsiusTemperature)
        }

    }
}