package com.example.weatherapp.models

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LocationService {

    private const val baseUrl: String = "https://restcountries.com/"

    fun getInstance() : Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
    }
}