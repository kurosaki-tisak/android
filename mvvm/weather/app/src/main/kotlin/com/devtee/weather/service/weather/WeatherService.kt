package com.devtee.weather.service.weather

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.support.annotation.VisibleForTesting
import com.devtee.weather.R
import com.devtee.weather.WeatherApplication
import com.devtee.weather.feature.ForecastItem
import com.devtee.weather.feature.WeatherItem
import com.devtee.weather.model.ForecastDayItem
import com.devtee.weather.model.Weather
import com.devtee.weather.service.base.ResponseType
import com.devtee.weather.service.enqueueWithProcessing
import com.devtee.weather.service.resource.ResourceService
import org.joda.time.format.DateTimeFormat

interface WeatherService {
    fun fetchCurrentWeather(lat: Double, lng: Double, day: Int): LiveData<ResponseType>
    fun getData(): LiveData<WeatherItem>
    fun refresh()
}

class WeatherServiceImpl(private val weatherAPI: WeatherAPI) : WeatherService {

    private val resourceService: ResourceService = WeatherApplication.applicationComponent.getResourceService()

    @VisibleForTesting
    val error = MutableLiveData<ResponseType>()

    private val data = MutableLiveData<WeatherItem>()

    private var location: String = ""
    private var day: Int = 0

    private val localDate = DateTimeFormat.forPattern("yyyy-MM-dd")
    private val degreeSymbol = '\u00B0'

    init {
        WeatherApplication.applicationComponent.inject(this)
    }

    override fun fetchCurrentWeather(lat: Double, lng: Double, day: Int): LiveData<ResponseType> {
        this.location = "$lat,$lng"
        this.day = day

        callService()

        return error
    }

    override fun getData(): LiveData<WeatherItem> {
        return data
    }

    override fun refresh() {
        data.value = null
        callService()
    }

    private fun callService() {
        val key = resourceService.getString(R.string.apixuApiKey)

        weatherAPI.getCurrentWeather(key, location, day)
            .enqueueWithProcessing(
                preProcessing = { it -> convertWeatherItem(it) },
                success = { data.value = it },
                fail = { responseType, _ -> error.value = responseType }
            )
    }

    private fun convertWeatherItem(item: Weather?): WeatherItem {
        return WeatherItem(
            currentLocation = "${item?.location?.region ?: item?.location?.country}",
            currentWeather = "${item?.current?.tempC}",
            forecastWeatherList = convertForecastItem(item?.forecast?.forecastDay) ?: emptyList()
        )
    }

    private fun convertForecastItem(list: List<ForecastDayItem?>?): List<ForecastItem>? {
        return list?.map { item ->
            ForecastItem(
                dayOfWeek = localDate.parseDateTime(item?.date).dayOfWeek().asText,
                avgTempC = "${item?.day?.avgtempC}" + degreeSymbol
            )

        } ?: emptyList()
    }
}