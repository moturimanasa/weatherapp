package com.example.myweatherapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    val weatherData = MutableLiveData<WeatherResponse>()
    val errorMessage = MutableLiveData<String>()

    fun getWeather(city: String, apiKey: String) {
        val viewModelScope = null
        viewModelScope?.launch {
            try {
                val result = repository.getWeather(city, apiKey)
                if (result != null) {
                    weatherData.postValue(result)
                } else {
                    errorMessage.postValue("Weather data not found.")
                }
            } catch (e: Exception) {
                errorMessage.postValue("Error occurred: ${e.message}")
            }
        }
    }

    // Load last searched city from Room database
    fun loadLastSearchedCity() {
        viewModelScope.launch {
            val lastCity = repository.getLastSearchedCity()
            lastCity?.let {
                getWeather(it.name, YOUR_API_KEY)
            }
        }
    }
    fun fetchWeather(city: String, apiKey: String) {
        // Launch a coroutine in the ViewModel
        viewModelScope.launch {
            try {
                // Call the suspend function to get weather data
                val weatherResponse = weatherRepository.getWeather(city, apiKey)
                // Use the weatherResponse as needed (e.g., updating LiveData)
            } catch (e: Exception) {
                // Handle errors such as network issues
                e.printStackTrace()
            }
        }
    }
}
