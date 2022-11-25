package com.example.weatherapp.viewmodel.recyclerview.mainview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.viewmodel.objects.Location
import com.example.weatherapp.view.viewactivities.DetailsActivity


class WeatherListAdapter(
    val context: Context
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var locationList: List<Location> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //TELLING THE RECYCLER VIEW TO CREATE THE VIEW HOLDERS IN THE LIST
        return WeatherViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_weather_card_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is WeatherViewHolder -> {
                holder.bind(locationList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        //TELLS RECYCLERVIEW HOW MANY ITEMS ARE INSIDE YOUR LIST
        return locationList.size
    }

    fun submitList(locationsList: List<Location>){
        this.locationList = locationsList
        notifyDataSetChanged()
    }

    inner class WeatherViewHolder(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
        private val weatherLocation: TextView = itemView.findViewById(R.id.location_text)
        private val weatherTemperature: TextView = itemView.findViewById(R.id.temperate_text)
        private val weatherButton: Button = itemView.findViewById(R.id.DetailsButton)

        fun bind(locationItem: Location){
            weatherLocation.text = locationItem.name.common
            weatherTemperature.text = locationItem.name.official
            weatherButton.setOnClickListener{
                val intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra("Location",weatherLocation.text.toString())
                context.startActivity(intent)
            }
        }

    }


}