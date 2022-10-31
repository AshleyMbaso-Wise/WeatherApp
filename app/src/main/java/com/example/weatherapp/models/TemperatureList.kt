package com.example.weatherapp.models

data class TemperatureList(
    val address: String,
    val days: List<TemperatureResult>,

)
