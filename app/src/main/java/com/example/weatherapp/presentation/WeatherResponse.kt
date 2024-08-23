package com.example.weatherapp.presentation

data class WeatherResponse(
    val main: Main
)

data class Main(
    val temp: Double,       // Agrega esta línea para la temperatura actual
    val temp_min: Double,
    val temp_max: Double,
    val humidity: Int
)
