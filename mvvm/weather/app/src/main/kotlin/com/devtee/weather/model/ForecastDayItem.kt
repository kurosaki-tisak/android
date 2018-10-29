package com.devtee.weather.model

import com.google.gson.annotations.SerializedName

data class ForecastDayItem(

	@field:SerializedName("date")
	val date: String?,

	@field:SerializedName("astro")
	val astro: Astro?,

	@field:SerializedName("date_epoch")
	val dateEpoch: Int?,

	@field:SerializedName("day")
	val day: Day?
)