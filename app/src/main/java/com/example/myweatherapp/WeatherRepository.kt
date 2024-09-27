package com.example.myweatherapp

import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherService: WeatherService,
    private val cityDao: CityDao
) {
    suspend fun getWeather(city: String, apiKey: String): WeatherResponse? {
        // Make the API call (this is also a suspend function)
        val response = weatherService.getWeatherByCity(city, apiKey)

        // Check if the response is successful
        if (response.isSuccessful) {
            response.body()?.let {
                // Insert city into the database (this is valid since we're in a coroutine)
                cityDao.insertCity(City(it.cityName)) // Suspension function called inside a suspend function
                return it // Return the weather response after saving the city
            }
        }

        // If the response is not successful, return null or handle the error accordingly
        return null
    }




    suspend fun getLastSearchedCity(): City? = cityDao.getLastCity()
}
