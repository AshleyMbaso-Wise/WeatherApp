package com.example.weatherapp.models

import com.example.weatherapp.models.WeatherPost

class WeatherDataSource{

    companion object{
        fun createWeatherDataSet(): ArrayList<WeatherPost>{
            val weatherDataList = ArrayList<WeatherPost>()

            weatherDataList.add(
                WeatherPost("London", "10 Celcius", "Rain")
            )

            weatherDataList.add(
                WeatherPost("Rome", "20 Celcius", "Sunny")
            )

            weatherDataList.add(
                WeatherPost("Madrid", "15 Celcius", "Windy")
            )

            weatherDataList.add(
                WeatherPost("Palermo", "30 Celcius", "Sunny")
            )

            weatherDataList.add(
                WeatherPost("Lagos", "20 Celcius", "Sunny")
            )

            weatherDataList.add(
                WeatherPost("Paris", "15 Celcius", "Windy")
            )

            weatherDataList.add(
                WeatherPost("Kiyv", "30 Celcius", "Sunny")
            )

            println(weatherDataList)

            return weatherDataList
        }
    }
}