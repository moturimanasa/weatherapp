package com.example.myweatherapp

import androidx.room.Query
import com.google.firebase.vertexai.common.Response

interface WeatherService {
    @GET("weather")
    suspend fun getWeatherByCity(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    ): Response<WeatherResponse>
}

