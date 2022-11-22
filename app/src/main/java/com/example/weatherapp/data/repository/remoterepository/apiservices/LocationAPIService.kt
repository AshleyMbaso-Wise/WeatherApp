package com.example.weatherapp.data.repository.remoterepository.apiservices

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LocationAPIService {

    private const val baseUrl: String = "https://restcountries.com/"

    fun getInstance() : Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
    }
}