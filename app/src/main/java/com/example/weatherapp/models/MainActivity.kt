package com.example.weatherapp.models

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import javax.sql.DataSource

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
        logMessage("WEATHER LIST", data)
        weatherAdapter.submitList(data)
    }

    private fun logMessage(tag: String, message: Any){
        Log.d(tag, message.toString())
    }

    private fun initRecyclerView(){
        val weatherData_recyclerView = findViewById<RecyclerView>(R.id.weather_recycler_view)
        weatherData_recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            weatherAdapter = WeatherListAdapter()
            adapter = weatherAdapter
        }
    }
}