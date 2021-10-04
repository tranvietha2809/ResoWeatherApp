package com.example.resoweatherapp.data.network

import androidx.lifecycle.LiveData
import com.example.resoweatherapp.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather : LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(query: String)
}