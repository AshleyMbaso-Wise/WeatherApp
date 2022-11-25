package com.example.weatherapp.data.repository.remoterepository.remoterepositoryobjects

data class LocationWeatherDaysData(
    val temp: Double,
    val humidity: String,
    val description: String,
    val icon: String,
){
    fun toTemperature(): String = "$temp °F"
}
