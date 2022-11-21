package com.example.weatherapp.models

import retrofit2.Retrofit

class LocationRepository {
    private val locationRetrofit: Retrofit by lazy { LocationService.getInstance() }
    private val locationAPI: LocationAPI by lazy { locationRetrofit.create(LocationAPI::class.java) }

    suspend fun getLocations(): List<Location>?{
        return locationAPI.getLocations().body()
    }

}