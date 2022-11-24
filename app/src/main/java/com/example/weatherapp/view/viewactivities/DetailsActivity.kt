package com.example.weatherapp.view.viewactivities

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.R
import com.example.weatherapp.viewmodel.uistate.WeatherDetailsUiState
import com.example.weatherapp.viewmodel.WeatherDetailsViewModel
import com.example.weatherapp.viewmodel.factories.DetailsViewModelFactory
import kotlinx.coroutines.*


class DetailsActivity: AppCompatActivity() {

    private val returnButton: Button by lazy { findViewById(R.id.return_button) }
    private val locationName: TextView by lazy { findViewById(R.id.detail_locationName)}
    private val locationTemperature: TextView by lazy { findViewById(R.id.detail_locationTemperature)}
    private val locationHumidity: TextView by lazy { findViewById(R.id.detail_locationHumidity)}
    private val locationDescription: TextView by lazy { findViewById(R.id.detail_locationDescription) }
    private val temperatureImage: ImageView by lazy { findViewById(R.id.TemperatureImage) }

    private val city: String by lazy { intent.getStringExtra("Temperature_Info").toString() }

    private val detailsViewModel: WeatherDetailsViewModel by viewModels() { DetailsViewModelFactory(city) }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_page)
        supportActionBar?.hide()

        lifecycleScope.launch {
            launchTemperatureCall()
            updateUiStateDetailsComponents()
        }

        returnButton.setOnClickListener{
            finish()
        }
    }

    private suspend fun updateUiStateDetailsComponents(){
        detailsViewModel.uiState.collect { uiState ->
            setComponentInfo(uiState)
        }
    }

    private suspend fun launchTemperatureCall(){
        detailsViewModel.requestTemperatureDetails()
    }

    private fun setComponentInfo(uiState: WeatherDetailsUiState){
        locationName.text = uiState.weatherInformation?.address
        val latestWeatherResultIndex: Int = 0
        val weatherInformation = uiState.weatherInformation?.days?.get(latestWeatherResultIndex)
        locationHumidity.text = weatherInformation?.humidity
        locationTemperature.text = weatherInformation?.temp.toString() + " °F"
        locationDescription.text = weatherInformation?.description
        setTemperaturePhoto(weatherInformation?.icon)
    }

    private fun setTemperaturePhoto(weatherStatus: String?){
        when(weatherStatus){
            "clear-day" -> temperatureImage.setImageResource(R.drawable.scorpion_clear_day)
            "rain" -> temperatureImage.setImageResource(R.drawable.squirtle_rain)
            "fog" -> temperatureImage.setImageResource(R.drawable.fog_fog)
            "cloudy" -> temperatureImage.setImageResource(R.drawable.lumpy_cloudy)
            else -> {
                temperatureImage.setImageResource(R.drawable.price)
            }
        }
    }


}