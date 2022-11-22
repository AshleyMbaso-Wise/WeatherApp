package com.example.weatherapp.data.repository.remoterepository.apiendpoints

import com.example.weatherapp.viewmodel.objects.Location
import retrofit2.Response
import retrofit2.http.GET

interface LocationAPI {
    @GET("/v3.1/all")
    suspend fun getLocations(): Response<List<Location>>
}