package com.example.clothingsuggester.presenter

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.clothingsuggester.R
import com.example.clothingsuggester.model.Weather
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ShowClothes(weather: Weather) {
    private val temperatureList = weather.data.timelines[0].intervals
    val temperatureOfDay = weather.data.timelines[0].intervals[0].values.temperature
    val freezing = mutableListOf<Int>()
    val cool = mutableListOf<Int>()
    val cold = mutableListOf<Int>()
    val mild = mutableListOf<Int>()
    val warm = mutableListOf<Int>()
    val hot = mutableListOf<Int>()
    val veryHot = mutableListOf<Int>()

    init {
        freezing.add(R.drawable.frezzing1)
        freezing.add(R.drawable.frezzing)
        freezing.add(R.drawable.frezzing3)

        cool.add(R.drawable.cold5)
        cool.add(R.drawable.warm)
        cool.add(R.drawable.hot5)


        cold.add(R.drawable.cold7)
        cold.add(R.drawable.cold9)
        cold.add(R.drawable.frezzing3)

        mild.add(R.drawable.mild5)
        mild.add(R.drawable.frezzing)
        mild.add(R.drawable.warm4)

        warm.add(R.drawable.warm1)
        warm.add(R.drawable.warm7)
        warm.add(R.drawable.warm5)

        hot.add(R.drawable.hot5)
        hot.add(R.drawable.hot4)
        hot.add(R.drawable.hot2)

        veryHot.add(R.drawable.hot2)
        veryHot.add(R.drawable.hot5)
        veryHot.add(R.drawable.hot4)



    }


    fun temperatureOfDay(): WeatherStatus {
        return when (temperatureOfDay) {
            in -80.0..0.0 -> WeatherStatus.Freezing
            in 1.0..15.0 -> WeatherStatus.Cold
            in 16.0..20.0 -> WeatherStatus.Cool
            in 21.0..25.0 -> WeatherStatus.Mild
            in 26.0..30.0 -> WeatherStatus.Warm
            in 31.0..35.0 -> WeatherStatus.Hot
            in 36.0..95.0 -> WeatherStatus.VeryHot
            else -> throw IllegalStateException("Invalid temperature")
        }

    }

    fun listOfClothes(): MutableList<Int> {
        return when (temperatureOfDay()) {
            WeatherStatus.Freezing -> freezing
            WeatherStatus.Cold -> cold
            WeatherStatus.Cool -> cool
            WeatherStatus.Mild -> mild
            WeatherStatus.Warm -> warm
            WeatherStatus.Hot -> hot
            WeatherStatus.VeryHot -> veryHot
        }

    }



    fun getEndTime(): String {
        val currentDateTime = LocalDateTime.now().plusDays(15)
        val iso8601Format = DateTimeFormatter.ISO_LOCAL_DATE_TIME
        return currentDateTime.format(iso8601Format)
    }
    fun temperatureList() {
        for (temperature in temperatureList) {
            when (temperature.values.temperature) {
                in -80.0..0.0 -> WeatherStatus.Freezing
                in 1.0..15.0 -> WeatherStatus.Cold
                in 16.0..20.0 -> WeatherStatus.Cool
                in 21.0..25.0 -> WeatherStatus.Mild
                in 26.0..30.0 -> WeatherStatus.Warm
                in 31.0..35.0 -> WeatherStatus.Hot
                in 36.0..95.0 -> WeatherStatus.VeryHot
                else -> throw IllegalStateException("Invalid temperature")
            }

        }
    }


}