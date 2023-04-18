package com.example.clothingsuggester.ui

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.clothingsuggester.AdapterClothes
import com.example.clothingsuggester.R
import com.example.clothingsuggester.databinding.FragmentWheatherBinding
import com.example.clothingsuggester.model.Weather
import com.example.clothingsuggester.presenter.ShowClothes
import com.example.clothingsuggester.presenter.StatusResponse
import com.example.clothingsuggester.presenter.WeatherPresenter


class WeatherFragment :
    BaseFragment<FragmentWheatherBinding>() {
    private val presenter: WeatherPresenter by lazy { WeatherPresenter(requireContext()) }
    lateinit var showClothes: ShowClothes
    override val bindingInflater: (LayoutInflater) -> FragmentWheatherBinding
        get() = FragmentWheatherBinding::inflate
    lateinit var listOfClothes : MutableList<Int>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        makeRequest()

    }


    private fun makeRequest() {

        presenter.getApikey()?.let { apikey ->

            presenter.request(
                "31.4165,31.8133",
                apikey,
                object : StatusResponse {
                    override fun onSuccess(weather: Weather) {
                        showClothes = ShowClothes(weather)
                        val statusOfWeather = showClothes.temperatureOfDay().name
                        val temperatureOfDay = showClothes.temperatureOfDay.toInt()
                        val statusWeatherImage = showClothes.temperatureOfDay().image
                        listOfClothes = showClothes.listOfClothes()

                        requireActivity().runOnUiThread {
                            binding.apply {
                                weatherStatusImage.setImageResource(statusWeatherImage!!)
                                weatherStatus.text = statusOfWeather
                                temperature.text=temperatureOfDay.toString()
                                setupViews()

                            }
                        }
                    }

                    override fun onError(error: String) {
                        Log.d("TAG", "onError:{$error} ")
                    }

                }

            )


        }

    }
    private fun setupViews() {
        val clothesAdapter = AdapterClothes {  }
        clothesAdapter.submitList(listOfClothes)
        binding.clotheImageRecyclerView.adapter = clothesAdapter
    }



}

