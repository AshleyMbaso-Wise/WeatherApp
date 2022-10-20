package com.example.weatherapp.models

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.models.WeatherPost


class WeatherListAdapter(
    val context: Context
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var weatherItems: List<WeatherPost> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //TELLING THE RECYCLER VIEW TO CREATE THE VIEW HOLDERS IN THE LIST
        return WeatherViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_weather_card_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("HOLDER", holder.toString())
        when(holder){
            is WeatherViewHolder -> {
                holder.bind(weatherItems.get(position))
            }
        }
        //val weatherData: WeatherPost = weatherItems.get(position)
    }

    override fun getItemCount(): Int {
        //TELLS RECYCLERVIEW HOW MANY ITEMS ARE INSIDE YOUR LIST
        return weatherItems.size
    }

    fun submitList(weatherItemsList: List<WeatherPost>){
        Log.d("SUBMIT LIST", weatherItemsList.toString())
        Log.d("SUBMIT LIST STARTING", weatherItems.toString())
        weatherItems = weatherItemsList
        Log.d("SUBMIT LIST FINISHED", weatherItems.toString())
    }

    inner class WeatherViewHolder(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
        val weatherLocation  = itemView.findViewById<TextView>(R.id.location_text)
        val weatherTemperature = itemView.findViewById<TextView>(R.id.temperate_text)
        val weatherButton = itemView.findViewById<Button>(R.id.DetailsButton)

        fun bind(weatherPostItem: WeatherPost){
            weatherLocation.setText(weatherPostItem.city)
            weatherTemperature.setText(weatherPostItem.temperature)
            weatherButton.setOnClickListener{
                val intent = Intent(context,DetailsActivity::class.java)
                context.startActivity(intent)
            }
        }

    }


}