package com.example.weatherapp.models

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val api_base_url = "https://weather.visualcrossing.com/"

    fun getInstance(): Retrofit{
        return Retrofit.Builder().baseUrl(api_base_url)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
}