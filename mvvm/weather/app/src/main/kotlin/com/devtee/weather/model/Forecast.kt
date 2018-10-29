package com.devtee.weather.model

import com.google.gson.annotations.SerializedName

data class Forecast(

	@field:SerializedName("forecastday")
	val forecastDay: List<ForecastDayItem?>?
)