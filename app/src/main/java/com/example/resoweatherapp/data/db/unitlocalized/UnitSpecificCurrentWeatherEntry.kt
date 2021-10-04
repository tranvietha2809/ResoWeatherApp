package com.example.resoweatherapp.data.db.unitlocalized

interface UnitSpecificCurrentWeatherEntry {
    val feelslike: Double
    val isDay: String
    val observationTime: String
    val precip: Double
    val temperature: Double
    val uvIndex: Double
    val visibility: Double
    val weatherCode: Int
    val weatherDescriptions: List<String>
    val weatherIcons: List<String>
    val windDir: String
    val windSpeed: Double
}