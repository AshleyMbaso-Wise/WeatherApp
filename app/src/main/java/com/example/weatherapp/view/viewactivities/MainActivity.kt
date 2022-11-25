package com.example.weatherapp.view.viewactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.viewmodel.recyclerview.mainview.WeatherListAdapter
import com.example.weatherapp.viewmodel.MainMenuViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var weatherAdapter: WeatherListAdapter
    private val mainMenuViewModel: MainMenuViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        lifecycleScope.launch{
            mainMenuViewModel.uiState.collect { currentWeatherState ->
                weatherAdapter.submitList(currentWeatherState.locations?: emptyList())
            }
        }
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