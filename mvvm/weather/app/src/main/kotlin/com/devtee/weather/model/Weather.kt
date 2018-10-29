package com.devtee.weather.model

import com.google.gson.annotations.SerializedName

data class Weather(

    @field:SerializedName("current")
    val current: Current?,

    @field:SerializedName("location")
    val location: Location?,

    @field:SerializedName("forecast")
    val forecast: Forecast?
)