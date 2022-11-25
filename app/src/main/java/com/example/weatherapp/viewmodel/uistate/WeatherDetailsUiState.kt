package com.example.weatherapp.viewmodel.uistate

import com.example.weatherapp.viewmodel.objects.LocationWeatherData

data class WeatherDetailsUiState(
    val isPageLoaded: Boolean = false,
    val weatherInformation: LocationWeatherData? = null,
)
