package com.example.weatherapp.models

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TemperatureApi {
    @GET("/VisualCrossingWebServices/rest/services/timeline/{location}?unitGroup=us&key=MD7PVZ5ZAEFGTP5524N3KE7C8&contentType=json")
    suspend fun getTemperature(
        @Path("location") text: String
    ): Response<TemperatureList>

}