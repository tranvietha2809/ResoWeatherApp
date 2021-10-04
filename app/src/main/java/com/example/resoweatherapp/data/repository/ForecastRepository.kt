package com.example.resoweatherapp.data.repository

import androidx.lifecycle.LiveData
import com.example.resoweatherapp.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean) : LiveData<out UnitSpecificCurrentWeatherEntry>
}