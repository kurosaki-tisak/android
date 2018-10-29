package com.devtee.weather.service.weather

import com.devtee.weather.model.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("forecast.json")
    fun getCurrentWeather(@Query("key") key: String,
                          @Query("q", encoded = true) latLng: String,
                          @Query("days") day: Int): Call<Weather>
}