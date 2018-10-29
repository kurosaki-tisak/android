package com.devtee.weather.service.location

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.location.Location
import android.location.LocationManager
import com.devtee.weather.common.livedata.SingleLiveEvent
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

interface WeatherLocationServices {
    fun setPermissionGranted(granted: Boolean)
    fun getLocation(): LiveData<Location?>
    fun getLocationServiceEnableStatus(): LiveData<Boolean>
}

class WeatherLocationServicesImpl(context: Context) : WeatherLocationServices {
    private val permissionGranted = MutableLiveData<Boolean>()
    private val location = MutableLiveData<Location?>()
    private val locationClient: FusedLocationProviderClient by lazy { LocationServices.getFusedLocationProviderClient(context) }
    private val locationManager: LocationManager by lazy { context.getSystemService(Context.LOCATION_SERVICE) as LocationManager }

    init {
        permissionGranted.observeForever { granted -> flushLocation(granted == true) }
    }

    override fun setPermissionGranted(granted: Boolean) {
        permissionGranted.value = granted
    }

    override fun getLocation(): LiveData<Location?> {
        return location
    }

    override fun getLocationServiceEnableStatus(): LiveData<Boolean> {
        val isEnabled = SingleLiveEvent<Boolean>()
        try {
            val gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val networkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            isEnabled.value = gpsEnabled && networkEnabled
        } catch (e: Exception) {
            isEnabled.value = false
        }
        return isEnabled
    }

    @SuppressLint("MissingPermission")
    private fun flushLocation(granted: Boolean) {
        if (granted) {
            locationClient.lastLocation.addOnCompleteListener { task ->
                if (task.isSuccessful) location.value = task.result
            }
        } else {
            location.value = null
        }
    }
}