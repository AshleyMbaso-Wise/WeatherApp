package com.example.weatherapp.models

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R

class DetailsActivity : AppCompatActivity() {

    private lateinit var returnButton: Button

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_page)

        returnButton = findViewById(R.id.return_button)
        returnButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


}