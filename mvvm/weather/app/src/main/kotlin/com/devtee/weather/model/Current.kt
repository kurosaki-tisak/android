package com.devtee.weather.model

import com.google.gson.annotations.SerializedName

data class Current(

	@field:SerializedName("feelslike_c")
	val feelslikeC: Double?,

	@field:SerializedName("last_updated")
	val lastUpdated: String?,

	@field:SerializedName("feelslike_f")
	val feelslikeF: Double?,

	@field:SerializedName("wind_degree")
	val windDegree: Int?,

	@field:SerializedName("last_updated_epoch")
	val lastUpdatedEpoch: Int?,

	@field:SerializedName("is_day")
	val isDay: Int?,

	@field:SerializedName("precip_in")
	val precipIn: Double?,

	@field:SerializedName("wind_dir")
	val windDir: String?,

	@field:SerializedName("temp_c")
	val tempC: Double?,

	@field:SerializedName("pressure_in")
	val pressureIn: Double?,

	@field:SerializedName("temp_f")
	val tempF: Double?,

	@field:SerializedName("precip_mm")
	val precipMm: Double?,

	@field:SerializedName("cloud")
	val cloud: Int?,

	@field:SerializedName("wind_kph")
	val windKph: Double?,

	@field:SerializedName("condition")
	val condition: Condition?,

	@field:SerializedName("wind_mph")
	val windMph: Double?,

	@field:SerializedName("vis_km")
	val visKm: Double?,

	@field:SerializedName("humidity")
	val humidity: Int?,

	@field:SerializedName("pressure_mb")
	val pressureMb: Double?,

	@field:SerializedName("vis_miles")
	val visMiles: Double?
)