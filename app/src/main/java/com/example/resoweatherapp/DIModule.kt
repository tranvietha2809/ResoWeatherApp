package com.example.resoweatherapp

import android.content.Context
import com.example.resoweatherapp.data.db.CurrentWeatherDAO
import com.example.resoweatherapp.data.db.ForecastDatabase
import com.example.resoweatherapp.data.network.*
import com.example.resoweatherapp.data.repository.ForecastRepository
import com.example.resoweatherapp.data.repository.ForecastRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DIModule {

    @Provides
    @Singleton
    fun provideForecastDatabase(@ApplicationContext context: Context) : ForecastDatabase = ForecastDatabase.invoke(context)

    @Provides
    @Singleton
    fun provideCurrentWeatherDao(db: ForecastDatabase): CurrentWeatherDAO = db.currentWeatherDAO()

    @Provides
    @Singleton
    fun provideConnectivityInterceptor(@ApplicationContext context: Context): ConnectivityInterceptor = ConnectivityInterceptorImpl(context)

    @Provides
    @Singleton
    fun provideWeatherAPIService(connectivityInterceptor: ConnectivityInterceptor): WeatherAPIService = WeatherAPIService.invoke(connectivityInterceptor)

    @Provides
    @Singleton
    fun provideWeatherNetworkDataSource(apiService: WeatherAPIService) : WeatherNetworkDataSource = WeatherNetworkDataSourceImpl(apiService)

    @Provides
    @Singleton
    fun provideForecastRepository(currentWeatherDAO: CurrentWeatherDAO, currentNetworkDataSource: WeatherNetworkDataSource) : ForecastRepository = ForecastRepositoryImpl(currentWeatherDAO, currentNetworkDataSource)

}