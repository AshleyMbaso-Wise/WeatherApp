package com.example.weatherapp.data.repository.remoterepository.remoterepositoryobjects

data class LocationWeatherRemoteData(
    val address: String?,
    val days: List<LocationWeatherDaysData>?,
)
