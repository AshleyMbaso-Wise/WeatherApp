package com.example.weatherapp.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.repository.LocationRepository
import com.example.weatherapp.viewmodel.uistate.WeatherMainMenuUiState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainMenuViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(WeatherMainMenuUiState())
    val uiState: StateFlow<WeatherMainMenuUiState> = _uiState.asStateFlow()

    private val locationRepository = LocationRepository()

    init {
        setListOfCountries()
    }

    private fun setListOfCountries() {
        viewModelScope.launch {
            val listOfCountries = locationRepository.getLocations()
            _uiState.update { currentState ->
                currentState.copy(
                    locations = listOfCountries?.sortedBy { location -> location.name.common },
                    isPageLoaded = true
                )
            }
        }
    }

    fun isPageLoaded(loaded: Boolean ){
        _uiState.update { currentState ->
            currentState.copy(isPageLoaded = loaded)
        }
    }
}