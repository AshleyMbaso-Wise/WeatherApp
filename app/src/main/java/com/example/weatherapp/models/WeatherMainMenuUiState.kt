package com.example.weatherapp.models

data class WeatherMainMenuUiState(
    val isPageLoaded: Boolean = false,
    val locations: List<Location>? = listOf(),
)

data class WeatherMainMenuItemState(
    val locationName: String,
    val locationContinent: String
)
