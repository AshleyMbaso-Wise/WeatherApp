package com.example.weatherapp.models

import retrofit2.Retrofit

data class WeatherDetailsUiState(
    val isPageLoaded: Boolean = false,
    val weatherInformation: LocationWeatherData? = null,
    val weatherRetroFit: Retrofit? = null,
)
