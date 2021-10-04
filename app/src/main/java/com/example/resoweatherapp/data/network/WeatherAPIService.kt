package com.example.resoweatherapp.data.network

import com.example.resoweatherapp.data.network.response.CurrentWeatherResponse
import com.example.resoweatherapp.util.Resources
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//http://api.weatherstack.com/current?access_key=eee068878df7658f2248539b72e27a0b&query=New%20York&language=en

interface WeatherAPIService {
    companion object{
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor): WeatherAPIService {
            val requestInterceptor = Interceptor {chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("access_key", Resources.WEATHER_API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }
            val loggingInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .addInterceptor(loggingInterceptor)
                .build()
            return Retrofit.Builder().client(okHttpClient).baseUrl("http://api.weatherstack.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory()).addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherAPIService::class.java)
        }
    }
    @GET("current")
    suspend fun getCurrentWeather(
        @Query("query") query: String,
    ) : CurrentWeatherResponse
}