package com.example.weatherapp.models

data class LocationWeatherRemoteData(
    val address: String?,
    val days: List<TemperatureData>?,
)
