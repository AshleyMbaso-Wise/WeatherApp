package com.example.weatherapp.models

import retrofit2.Response
import retrofit2.http.GET

interface LocationAPI {
    @GET("/v3.1/all")
    suspend fun getLocations(): Response<List<Location>>
}