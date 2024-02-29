package com.example.assignmentph.di

import android.content.Context
import androidx.room.Room
import com.example.assignmentph.data.local.WeatherDao
import com.example.assignmentph.data.local.WeatherDatabase
import com.example.assignmentph.data.remote.WeatherApi
import com.example.assignmentph.data.repository.WeatherRepository
import com.example.assignmentph.data.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherApi():WeatherApi {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase {
        return WeatherDatabase.getDatabase(context)
    }

    @Provides
    fun provideWeatherDao(weatherDatabase: WeatherDatabase): WeatherDao {
        return weatherDatabase.weatherDao()
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherApi: WeatherApi, weatherDatabase: WeatherDatabase): WeatherRepository {
        return WeatherRepositoryImpl(weatherApi, weatherDatabase)
    }
}