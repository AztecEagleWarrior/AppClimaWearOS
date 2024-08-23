package com.example.weatherapp.presentation

import com.example.weatherapp.R
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    private val apiKey = "44e2ba3fe66d3196ac23de4459d9bd21"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextCity = findViewById<EditText>(R.id.editTextCity)
        val buttonGetWeather = findViewById<Button>(R.id.buttonGetWeather)
        val textViewWeather = findViewById<TextView>(R.id.textViewWeather)

        buttonGetWeather.setOnClickListener {
            val city = editTextCity.text.toString()

            if (city.isNotEmpty()) {
                getWeatherData(city, textViewWeather)
            } else {
                textViewWeather.text = "Ciudad"
            }
        }
    }

    private fun getWeatherData(city: String, textViewWeather: TextView) {
        val call = RetrofitClient.apiService.getWeatherData(city, apiKey)

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful) {
                    val weather = response.body()
                    val tempActual = weather?.main?.temp
                    val tempMin = weather?.main?.temp_min
                    val tempMax = weather?.main?.temp_max
                    val humidity = weather?.main?.humidity

                    textViewWeather.text = """
                        TempAct: $tempActual°C
                        Min: $tempMin°C
                        Max: $tempMax°C
                        Humedad: $humidity%
                    """.trimIndent()
                } else {
                    textViewWeather.text = "Ciudad no encontrada"
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                textViewWeather.text = "Hubo un error al recibir los datos"
                Log.e("MainActivity", t.message.toString())
            }
        })
    }
}
