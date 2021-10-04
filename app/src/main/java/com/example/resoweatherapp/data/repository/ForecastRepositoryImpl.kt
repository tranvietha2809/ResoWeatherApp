package com.example.resoweatherapp.data.repository

import androidx.lifecycle.LiveData
import com.example.resoweatherapp.data.db.CurrentWeatherDAO
import com.example.resoweatherapp.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry
import com.example.resoweatherapp.data.network.WeatherNetworkDataSource
import com.example.resoweatherapp.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val currentWeatherDAO: CurrentWeatherDAO,
    private val currentWeatherNetworkDataSource: WeatherNetworkDataSource) : ForecastRepository {

    init {
        currentWeatherNetworkDataSource.downloadedCurrentWeather.observeForever{
            persistFetchedCurrentWeather(it)
        }
    }
    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry> {
        return withContext(Dispatchers.IO){
            initWeatherData()
            return@withContext if (metric) currentWeatherDAO.getWeatherMetric()
            else currentWeatherDAO.getWeatherMetric()
        }
    }

    private suspend fun initWeatherData(){
        if(isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1))){
            fetchCurrentWeather()
        }
    }

    private suspend fun fetchCurrentWeather(){
        currentWeatherNetworkDataSource.fetchCurrentWeather("London")
    }
    private suspend fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime) : Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }

    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDAO.upsert(fetchedWeather.currentWeatherEntry)
        }
    }
}