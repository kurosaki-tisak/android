package com.devtee.weather.model

import com.google.gson.annotations.SerializedName

data class Location(

    @field:SerializedName("localtime")
    val localtime: String?,

    @field:SerializedName("country")
    val country: String?,

    @field:SerializedName("localtime_epoch")
    val localtimeEpoch: Int?,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("lon")
    val lon: Double?,

    @field:SerializedName("region")
    val region: String?,

    @field:SerializedName("lat")
    val lat: Double?,

    @field:SerializedName("tz_id")
    val tzId: String?
)