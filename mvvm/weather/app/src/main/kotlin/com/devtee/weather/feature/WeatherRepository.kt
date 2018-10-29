package com.devtee.weather.feature

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.location.Location
import com.devtee.weather.WeatherApplication
import com.devtee.weather.common.errorloading.ErrorLoadingRepository
import com.devtee.weather.service.base.ResponseType
import com.devtee.weather.service.weather.WeatherService
import javax.inject.Inject

interface WeatherRepository : ErrorLoadingRepository<WeatherItem> {
    fun fetchWeather(lat: Double, lng: Double, day: Int)
    var location: MutableLiveData<Location>
}

class WeatherRepositoryImpl : WeatherRepository {

    @Inject
    lateinit var weatherService: WeatherService

    private val errors = MediatorLiveData<ResponseType>()

    private var lat: Double = 0.0
    private var lng: Double = 0.0
    private var day: Int = 0

    init {
        WeatherApplication.applicationComponent.inject(this)
    }

    override var location = MutableLiveData<Location>()
    override fun getData(): LiveData<WeatherItem> = weatherService.getData()
    override fun getErrors(): LiveData<ResponseType> = errors

    override fun retry() {
        fetchWeather(lat, lng, day)
    }

    override fun fetchWeather(lat: Double, lng: Double, day: Int) {
        this.lat = lat
        this.lng = lng
        this.day = day

        val error = weatherService.fetchCurrentWeather(lat, lng, day)
        errors.removeSource(error)
        errors.addSource(error) { errors.value = it }
    }
}

