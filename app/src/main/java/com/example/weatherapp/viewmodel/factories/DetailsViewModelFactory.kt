package com.example.weatherapp.viewmodel.factories

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.viewmodel.WeatherDetailsViewModel

class DetailsViewModelFactory(private val city: String): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeatherDetailsViewModel(SavedStateHandle(), city) as T
    }
}