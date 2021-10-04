package com.example.resoweatherapp.data.network.response


import com.example.resoweatherapp.data.db.entity.CurrentWeatherEntry
import com.example.resoweatherapp.data.db.entity.Location
import com.example.resoweatherapp.data.db.entity.Request
import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    @SerializedName("location")
    val location: Location,
    @SerializedName("request")
    val request: Request
)