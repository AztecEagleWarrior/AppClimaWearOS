package com.example.weatherapp.presentation

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface WeatherService {
    @GET("weather")
    fun getWeatherData(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric" //obtener datos en grados Celsius
    ): Call<WeatherResponse>
}