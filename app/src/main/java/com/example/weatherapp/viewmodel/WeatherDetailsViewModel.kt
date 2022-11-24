package com.example.weatherapp.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.weatherapp.viewmodel.objects.LocationWeatherData
import com.example.weatherapp.data.repository.remoterepository.remoterepositoryobjects.LocationWeatherRemoteData
import com.example.weatherapp.data.repository.WeatherDetailsRepository
import com.example.weatherapp.viewmodel.objects.Location
import com.example.weatherapp.viewmodel.uistate.WeatherDetailsUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext

class WeatherDetailsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val city: String,
): ViewModel() {
    private val _uiState = MutableStateFlow(WeatherDetailsUiState())
    val uiState: StateFlow<WeatherDetailsUiState> = _uiState.asStateFlow()
    private val locationWeatherRepository = WeatherDetailsRepository()
    private val savedStateHandle_LOCATION_KEY = "location"

    fun isPageLoaded(loaded: Boolean ){
        _uiState.update { currentState ->
            currentState.copy(isPageLoaded = loaded)
        }
    }

    init {
        setLocation(city)
    }

    private fun setLocation(location: String){
        savedStateHandle[savedStateHandle_LOCATION_KEY] = location
    }

    suspend fun requestTemperatureDetails() {
        val remoteDataResponse =
            withContext(Dispatchers.IO) {
                if(savedStateHandle.contains(savedStateHandle_LOCATION_KEY)){
                    locationWeatherRepository.getTemperatureDetails(savedStateHandle["location"]!!)
                }else{
                    throw Exception("NO LOCATION PRESENT")
                }
            }
        val convertedRemoteDataResponse = convertRemoteDataToViewModelData(remoteDataResponse)
        updateLocationWeatherData(convertedRemoteDataResponse)
    }

    private fun updateLocationWeatherData(selectedLocationWeatherData: LocationWeatherData?) {
        _uiState.update { currentState ->
            currentState.copy(weatherInformation = selectedLocationWeatherData)
        }
    }

    private fun convertRemoteDataToViewModelData(remoteDataResponse: LocationWeatherRemoteData?): LocationWeatherData {
        return LocationWeatherData(
            address = remoteDataResponse?.address,
            days = remoteDataResponse?.days
        )
    }
}