package com.example.weatherapp.models

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R

class MainActivity : AppCompatActivity() {

    private lateinit var weatherAdapter: WeatherListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        addDataSet()
    }

    private fun addDataSet(){
        val data = WeatherDataSource.createWeatherDataSet()
        weatherAdapter.submitList(data)
    }

    //APPLY helps with organising the code
    /*
        this = RecyclerView
        Therefore with apply, I don't need to write
        weatherData_recyclerView.layoutManager/weatherAdapter/adapter

        I can change properties within apply

     */
    private fun initRecyclerView(){
        val weatherDataRV: RecyclerView = findViewById(R.id.weather_recycler_view)
        weatherDataRV.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            weatherAdapter = WeatherListAdapter(this@MainActivity)
            adapter = weatherAdapter
        }
    }
}