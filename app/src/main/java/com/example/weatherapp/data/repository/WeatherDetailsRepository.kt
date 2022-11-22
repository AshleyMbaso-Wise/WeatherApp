package com.example.weatherapp.data.repository

import com.example.weatherapp.data.repository.remoterepository.remoterepositoryobjects.LocationWeatherRemoteData
import com.example.weatherapp.data.repository.remoterepository.apiservices.WeatherDataAPIService
import com.example.weatherapp.data.repository.remoterepository.apiendpoints.WeatherTemperatureAPI
import retrofit2.Retrofit

class WeatherDetailsRepository {

    private val weatherDetailsRetrofit: Retrofit by lazy { WeatherDataAPIService.getInstance() }
    private val weatherTemperatureAPI by lazy { weatherDetailsRetrofit.create(WeatherTemperatureAPI::class.java) }

    suspend fun getTemperatureDetails(location: String) : LocationWeatherRemoteData? {
        return weatherTemperatureAPI.getTemperature(location).body()
    }
}