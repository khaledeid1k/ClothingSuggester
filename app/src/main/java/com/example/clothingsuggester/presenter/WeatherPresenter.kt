package com.example.clothingsuggester.presenter

import android.content.Context
import com.example.clothingsuggester.data.SharedPreferencesServiceImpl
import com.example.clothingsuggester.model.Weather
import com.example.clothingsuggester.utils.Constants
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class WeatherPresenter(var context: Context) {
    private lateinit var sharedPreferencesServiceImpl: SharedPreferencesServiceImpl
    private val client = OkHttpClient()


    fun request(
        location: String,
        apikey: String,
        callback: StatusResponse
    ) {

        val url =buildUrl(location,apikey)

        val request = Request.Builder().url(url)
            .apply {
                get()
            }.build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onError(e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val body = response.body?.string()
                    val jason = Gson().fromJson(body, Weather::class.java)
                    callback.onSuccess(jason)
                } else {
                    callback.onError("Lost NetWork")
                }
            }
        })

    }

    fun getApikey(): String? {
        sharedPreferencesServiceImpl = SharedPreferencesServiceImpl(
            context.getSharedPreferences(
                Constants.PREFS_NAME,
                Context.MODE_PRIVATE
            ), context
        )
        sharedPreferencesServiceImpl.saveToken("TUJsFL56HLfHoq0RgY517rkPNAsr3u2O")
        //   removeTokenAndExpiryIfTokenInvalid(sharedPreferencesServiceImpl)
        return sharedPreferencesServiceImpl.getToken()
    }

    private fun buildUrl(
        location: String,
        apikey: String,
    ) = HttpUrl.Builder().scheme("https").host("api.tomorrow.io")
        .addPathSegment("v4")
        .addPathSegment("timelines")
        .addQueryParameter("location", location)
        .addQueryParameter("fields", "temperature")
        .addQueryParameter("timesteps", "1h")
        .addQueryParameter("units", "metric")
        .addQueryParameter("apikey", apikey)
        .build()

}


