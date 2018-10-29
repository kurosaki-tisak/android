package com.devtee.weather.feature

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.location.Location
import com.devtee.weather.WeatherApplication
import com.devtee.weather.common.map

data class WeatherItem(
    val currentLocation: String,
    val currentWeather: String,
    val forecastWeatherList: List<ForecastItem>
)

data class ForecastItem(
    val dayOfWeek: String,
    val avgTempC: String
)

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    val headerWeather = ObservableField<String>()
    val city = ObservableField<String>()
    val itemsList = MutableLiveData<List<ForecastItem>>()

    val location: LiveData<Location> = repository.location.map {
        fetchData(it)
        it
    }

    val weatherData: LiveData<WeatherItem> = repository.getData().map {
        setupDataToView(it)
        it
    }

    init {
        WeatherApplication.applicationComponent.inject(this)
    }

    private fun fetchData(location: Location) {
        repository.fetchWeather(location.latitude, location.longitude, 5)
    }

    private fun setupDataToView(item: WeatherItem) {
        val degreeSymbol = '\u00B0'
        headerWeather.set(item.currentWeather + degreeSymbol)
        city.set(item.currentLocation)

        val removed = item.forecastWeatherList.toMutableList()
        removed.removeAt(0)

        itemsList.value = removed
    }
}