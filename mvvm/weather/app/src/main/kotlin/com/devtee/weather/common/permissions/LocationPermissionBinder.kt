package com.devtee.weather.common.permissions

import android.Manifest
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.location.Location
import android.support.v7.app.AppCompatActivity
import com.devtee.weather.WeatherApplication
import com.tbruyelle.rxpermissions2.RxPermissions

class LocationPermissionBinder(observer: Observer<Location?>) {

    private var baseObserver: Observer<Location?> = observer

    private val locationServices = WeatherApplication.applicationComponent.getLocationService()

    val lastLocation = locationServices.getLocation().value

    fun bindTo(activity: AppCompatActivity) {
        RxPermissions(activity).requestLocation()
        observeLocation(activity)
    }

    private fun RxPermissions.requestLocation() = this
        .request(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
        .subscribe { granted -> locationServices.setPermissionGranted(granted) }

    private fun observeLocation(owner: LifecycleOwner) {
        locationServices.getLocation().observe(owner, baseObserver)
    }
}