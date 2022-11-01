package com.example.weatherapp.models

data class TemperatureResult(
    val temp: Double,
    val humidity: String,
    val description: String,
    val icon: String,
)
