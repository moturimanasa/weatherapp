package com.example.myweatherapp

data class WeatherResponse(
    @SerializedName("main") val main: Main,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("name") val cityName: String
) {
    val temperature: Double
        get() = main.temp - 273.15  // Convert from Kelvin to Celsius

    val description: String
        get() = weather.first().description.capitalize()

    val iconUrl: String
        get() = "http://openweathermap.org/img/wn/${weather.first().icon}.png"
}

annotation class SerializedName(val value: String)

data class Main(val temp: Double)
data class Weather(val description: String, val icon: String)

