package com.example.clothingsuggester

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.clothingsuggester.ui.WeatherFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        loadFragment()
    }
    fun loadFragment(){
        supportFragmentManager.beginTransaction().add(R.id.fragment_container_activity,
            WeatherFragment())
            .commit()
    }
}