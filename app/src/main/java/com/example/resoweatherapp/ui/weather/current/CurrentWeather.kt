package com.example.resoweatherapp.ui.weather.current

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.resoweatherapp.data.network.ConnectivityInterceptor
import com.example.resoweatherapp.data.network.ConnectivityInterceptorImpl
import com.example.resoweatherapp.data.network.WeatherAPIService
import com.example.resoweatherapp.data.network.WeatherNetworkDataSourceImpl
import com.example.resoweatherapp.databinding.CurrentWeatherFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CurrentWeather : Fragment() {

    companion object {
        fun newInstance() = CurrentWeather()
    }

    private lateinit var viewModel: CurrentWeatherViewModel
    private lateinit var currentWeatherFragmentBinding: CurrentWeatherFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        currentWeatherFragmentBinding = CurrentWeatherFragmentBinding.inflate(layoutInflater)
        val weatherAPIService = WeatherAPIService(ConnectivityInterceptorImpl(requireContext()))
        val weatherNetworkDataSource = WeatherNetworkDataSourceImpl(weatherAPIService)

        weatherNetworkDataSource.downloadedCurrentWeather.observe(viewLifecycleOwner, Observer {
            currentWeatherFragmentBinding.currentWeatherText.text = it.toString()
        })

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            weatherNetworkDataSource.fetchCurrentWeather("London")
        }
        return currentWeatherFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CurrentWeatherViewModel::class.java)
        // TODO: Use the ViewModel
    }

}