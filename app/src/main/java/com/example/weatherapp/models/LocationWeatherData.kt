package com.example.weatherapp.models

data class LocationWeatherData(
    val address: String?,
    val days: List<TemperatureData>?,
)
