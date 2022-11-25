package com.example.weatherapp.viewmodel.objects

import com.example.weatherapp.data.repository.remoterepository.remoterepositoryobjects.LocationWeatherDaysData

data class LocationWeatherData(
    val address: String?,
    val days: List<LocationWeatherDaysData>?,
)
