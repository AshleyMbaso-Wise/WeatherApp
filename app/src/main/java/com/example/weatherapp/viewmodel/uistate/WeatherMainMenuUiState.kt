package com.example.weatherapp.viewmodel.uistate

import com.example.weatherapp.viewmodel.objects.Location

data class WeatherMainMenuUiState(
    val isPageLoaded: Boolean = false,
    val locations: List<Location>? = listOf(),
)
