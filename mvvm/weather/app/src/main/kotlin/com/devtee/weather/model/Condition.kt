package com.devtee.weather.model

import com.google.gson.annotations.SerializedName

data class Condition(

	@field:SerializedName("code")
	val code: Int?,

	@field:SerializedName("icon")
	val icon: String?,

	@field:SerializedName("text")
	val text: String?
)