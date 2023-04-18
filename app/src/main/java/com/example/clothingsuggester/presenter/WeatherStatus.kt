package com.example.clothingsuggester.presenter

import com.example.clothingsuggester.R

enum class WeatherStatus(val image: Int?) {
    Freezing(R.drawable.freezing),
    Cold(R.drawable.cold),
    Cool(R.drawable.cool),
    Mild(R.drawable.mild),
    Warm(R.drawable.warm),
    Hot(R.drawable.hot),
    VeryHot(R.drawable.very_hot)

}