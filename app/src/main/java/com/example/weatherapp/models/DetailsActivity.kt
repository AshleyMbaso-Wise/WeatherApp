package com.example.weatherapp.models

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.R
import kotlinx.coroutines.*

class DetailsActivity: AppCompatActivity() {

    private val returnButton: Button by lazy { findViewById(R.id.return_button) }
    private val locationName: TextView by lazy { findViewById(R.id.detail_locationName)}
    private val locationTemperature: TextView by lazy { findViewById(R.id.detail_locationTemperature)}
    private val locationHumidity: TextView by lazy { findViewById(R.id.detail_locationHumidity)}
    private val locationDescription: TextView by lazy { findViewById(R.id.detail_locationDescription) }
    private val temperatureImage: ImageView by lazy { findViewById(R.id.TemperatureImage) }

    private val city: String by lazy { intent.getStringExtra("Temperature_Info").toString() }

    private fun initialiseComponentInfo(): Unit{
        locationName.text = city
    }

    private fun setComponentInfo(temperatureInfo: TemperatureResult?){
        if(temperatureInfo==null){
            return
        }
        locationHumidity.text = temperatureInfo.humidity
        locationTemperature.text = temperatureInfo.temp.toString() + " °F"
        locationDescription.text = temperatureInfo.description
        setTemperaturePhoto(temperatureInfo.icon)
    }

    private fun setTemperaturePhoto(weatherStatus: String){
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

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_page)
        initialiseComponentInfo()

        val retrofithelper = RetrofitHelper.getInstance()
        val temperatureApi = retrofithelper.create(TemperatureApi::class.java)
        supportActionBar?.hide()

        lifecycleScope.launch {
                /*Main thread is responsible for rendering all the frames of the screen
                    Each piece of code originally runs on the main thread
                 */
            val result = withContext(Dispatchers.IO){
                temperatureApi.getTemperature(city)
            }
                val resultInfo = result.body()?.days?.get(0)
                setComponentInfo(resultInfo)
                if (result != null)
                // Checking the results
                    Log.d("Temperature Results ", result.body().toString())
        }

        returnButton.setOnClickListener{
            finish()
        }
    }


}