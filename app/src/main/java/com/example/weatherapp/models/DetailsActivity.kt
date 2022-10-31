package com.example.weatherapp.models

import android.content.ClipDescription
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.R
import kotlinx.coroutines.*

import okhttp3.Dispatcher
import org.w3c.dom.Text

class DetailsActivity(
    var city: String = ""
): AppCompatActivity() {

    private lateinit var returnButton: Button

    private lateinit var locationName: TextView
    private lateinit var locationTemperature: TextView
    private lateinit var locationHumidity: TextView
    private lateinit var locationDescription: TextView
    private lateinit var temperatureImage: ImageView
    private lateinit var weatherSymbol: String

    private var dataLoaded: Boolean = false

    fun initialiseComponents(): Unit{
        locationName = findViewById(R.id.detail_locationName)
        locationTemperature = findViewById(R.id.detail_locationTemperature)
        locationHumidity = findViewById(R.id.detail_locationHumidity)
        locationDescription = findViewById(R.id.detail_locationDescription)
        returnButton = findViewById(R.id.return_button)
        temperatureImage = findViewById(R.id.TemperatureImage)
    }

    fun initialiseComponentInfo(): Unit{
        city = intent.getStringExtra("Temperature_Info").toString()
        locationName.setText(city)
    }

    fun setComponentInfo(temperatureInfo: TemperatureResult?){
        if(temperatureInfo==null){
            return
        }
        locationHumidity.setText(temperatureInfo.humidity)
        locationTemperature.setText(temperatureInfo.temp.toString() + " °F")
        locationDescription.setText(temperatureInfo.description)
        setTemperaturePhoto(temperatureInfo.icon)

        dataLoaded=true;//When the information has been updated, set this to true
    }

    fun setTemperaturePhoto(weatherStatus: String){
        Log.d("weather status", weatherStatus)
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

        initialiseComponents()
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
                    Log.d("HELLO THERE: ", result.body().toString())
        }


        returnButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


}