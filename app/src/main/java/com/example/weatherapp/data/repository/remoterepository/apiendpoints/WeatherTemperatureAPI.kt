package com.example.weatherapp.data.repository.remoterepository.apiendpoints

import com.example.weatherapp.data.repository.remoterepository.remoterepositoryobjects.LocationWeatherRemoteData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherTemperatureAPI {
    @GET("/VisualCrossingWebServices/rest/services/timeline/{location}?unitGroup=us&key=MD7PVZ5ZAEFGTP5524N3KE7C8&contentType=json")
    suspend fun getTemperature(
        @Path("location") text: String
    ): Response<LocationWeatherRemoteData>
}