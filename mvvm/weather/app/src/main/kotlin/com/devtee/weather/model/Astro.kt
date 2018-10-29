package com.devtee.weather.model

import com.google.gson.annotations.SerializedName

data class Astro(

	@field:SerializedName("moonset")
	val moonset: String?,

	@field:SerializedName("sunrise")
	val sunrise: String?,

	@field:SerializedName("sunset")
	val sunset: String?,

	@field:SerializedName("moonrise")
	val moonrise: String?
)