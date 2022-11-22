package com.example.weatherapp.data.repository

import com.example.weatherapp.viewmodel.objects.Location
import com.example.weatherapp.data.repository.remoterepository.apiendpoints.LocationAPI
import com.example.weatherapp.data.repository.remoterepository.apiservices.LocationAPIService
import retrofit2.Retrofit

class LocationRepository {
    private val locationRetrofit: Retrofit by lazy { LocationAPIService.getInstance() }
    private val locationAPI: LocationAPI by lazy { locationRetrofit.create(LocationAPI::class.java) }

    suspend fun getLocations(): List<Location>?{
        return locationAPI.getLocations().body()
    }

}