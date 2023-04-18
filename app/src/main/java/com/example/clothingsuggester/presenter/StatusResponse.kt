package com.example.clothingsuggester.presenter

import android.icu.math.BigDecimal
import android.view.SurfaceControl
import com.example.clothingsuggester.model.Weather

interface StatusResponse {
    fun onSuccess(weather: Weather)
    fun onError(error: String)
}
