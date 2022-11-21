package com.example.weatherapp.models

import kotlinx.coroutines.flow.MutableStateFlow
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class WeatherMainMenuViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(WeatherMainMenuUiState())
    val uiState: StateFlow<WeatherMainMenuUiState> = _uiState.asStateFlow()

    private val locationRepository = LocationRepository()

    suspend fun setListOfCountries() {
        val listOfCountries = locationRepository.getLocations()
        _uiState.update { currentState ->
            currentState.copy(
                locations = listOfCountries?.sortedBy { location -> location.name.common },
                isPageLoaded = true
            )
        }
    }

    fun isPageLoaded(loaded: Boolean ){
        _uiState.update { currentState ->
            currentState.copy(isPageLoaded = loaded)
        }
    }
}