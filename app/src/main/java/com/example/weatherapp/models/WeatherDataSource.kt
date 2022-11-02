package com.example.weatherapp.models

class WeatherDataSource{

    companion object{
        fun createWeatherDataSet(): List<WeatherPost> {
            return listOf(
                WeatherPost("London", "10 Celcius"),
                WeatherPost("Rome", "20 Celcius"),
                WeatherPost("Madrid", "15 Celcius"),
                WeatherPost("Palermo", "30 Celcius"),
                WeatherPost("Lagos", "20 Celcius"),
                WeatherPost("Paris", "15 Celcius"),
                WeatherPost("Kiev", "30 Celcius"),
                WeatherPost("Bologna", "20 Celcius"),
                WeatherPost("Barcelona", "40 Celcius")
            )
        }
    }
}