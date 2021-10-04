package com.example.resoweatherapp.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.resoweatherapp.data.network.response.CurrentWeatherResponse
import com.example.resoweatherapp.internal.NoConnectivityException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherNetworkDataSourceImpl @Inject constructor(private val apiService: WeatherAPIService) : WeatherNetworkDataSource {

    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(query: String) {
        try{
            withContext(Dispatchers.IO){
                val fetchedCurrentWeather = apiService.getCurrentWeather(query)
                withContext(Dispatchers.Main){
                    _downloadedCurrentWeather.postValue(fetchedCurrentWeather)
                }
            }
        } catch (e: NoConnectivityException){
            Log.e("Connectivity", "No Internet Connection.", e)
        }
    }
}