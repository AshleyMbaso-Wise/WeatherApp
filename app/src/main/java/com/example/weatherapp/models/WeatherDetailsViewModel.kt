package com.example.weatherapp.models

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext

class WeatherDetailsViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(WeatherDetailsUiState())
    val uiState: StateFlow<WeatherDetailsUiState> = _uiState.asStateFlow()

    private val locationWeatherRepository = WeatherDetailsRepository()

    private fun updateLocationWeatherData(selectedLocationWeatherData: LocationWeatherData?) {
        _uiState.update { currentState ->
            currentState.copy(weatherInformation = selectedLocationWeatherData)
        }
    }

    fun isPageLoaded(loaded: Boolean ){
        _uiState.update { currentState ->
            currentState.copy(isPageLoaded = loaded)
        }
    }

    private fun convertRemoteDataToViewModelData(remoteDataResponse: LocationWeatherRemoteData?): LocationWeatherData{
        return LocationWeatherData(
            address = remoteDataResponse?.address,
            days = remoteDataResponse?.days
        )
    }

    suspend fun setTemperatureDetails(location: String) {
        val remoteDataResponse =
            withContext(Dispatchers.IO) {
                locationWeatherRepository.getTemperatureDetails(location)
            }
        val convertedRemoteDataResponse = convertRemoteDataToViewModelData(remoteDataResponse)
        updateLocationWeatherData(convertedRemoteDataResponse)
    }
}