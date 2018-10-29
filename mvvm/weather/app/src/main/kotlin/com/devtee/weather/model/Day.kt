package com.devtee.weather.model

import com.google.gson.annotations.SerializedName

data class Day(

    @field:SerializedName("avgvis_km")
    val avgvisKm: Double?,

    @field:SerializedName("uv")
    val uv: Double?,

    @field:SerializedName("avgtemp_f")
    val avgtempF: Double?,

    @field:SerializedName("avgtemp_c")
    val avgtempC: Double?,

    @field:SerializedName("maxtemp_c")
    val maxtempC: Double?,

    @field:SerializedName("maxtemp_f")
    val maxtempF: Double?,

    @field:SerializedName("mintemp_c")
    val mintempC: Double?,

    @field:SerializedName("avgvis_miles")
    val avgvisMiles: Double?,

    @field:SerializedName("mintemp_f")
    val mintempF: Double?,

    @field:SerializedName("totalprecip_in")
    val totalprecipIn: Double?,

    @field:SerializedName("avghumidity")
    val avghumidity: Double?,

    @field:SerializedName("condition")
    val condition: Condition?,

    @field:SerializedName("maxwind_kph")
    val maxwindKph: Double?,

    @field:SerializedName("maxwind_mph")
    val maxwindMph: Double?,

    @field:SerializedName("totalprecip_mm")
    val totalprecipMm: Double?
)