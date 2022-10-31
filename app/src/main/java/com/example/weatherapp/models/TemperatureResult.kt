package com.example.weatherapp.models

data class TemperatureResult(
    var temp: Double,
    var humidity: String,
    var description: String,
    var icon: String,
)
