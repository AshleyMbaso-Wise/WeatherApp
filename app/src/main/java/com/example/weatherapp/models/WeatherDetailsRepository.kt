package com.example.weatherapp.models

import retrofit2.Response
import retrofit2.Retrofit

class WeatherDetailsRepository {

    private val weatherDetailsRetrofit: Retrofit by lazy {WeatherDataService.getInstance()}
    private val weatherTemperatureAPI by lazy { weatherDetailsRetrofit.create( WeatherTemperatureAPI::class.java) }

    suspend fun getTemperatureDetails(location: String) : LocationWeatherRemoteData? {
        return weatherTemperatureAPI.getTemperature(location).body()
    }
}