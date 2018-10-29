package com.devtee.weather.feature

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.devtee.weather.R
import com.devtee.weather.common.errorloading.ErrorLoadingBinder
import com.devtee.weather.common.permissions.LocationPermissionBinder
import com.devtee.weather.databinding.ActivityWeatherBinding

class WeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityWeatherBinding = DataBindingUtil.setContentView(this, R.layout.activity_weather)

        val repository = WeatherRepositoryImpl()

        val locationPermissionBinder = LocationPermissionBinder(Observer { repository.location.value = it })

        val binder = WeatherBinder(this, binding, repository)
        val errorBinder = ErrorLoadingBinder(this, repository, binding.partialScreen!!)

        binder.bindTo(this)
        errorBinder.bindTo(this)

        locationPermissionBinder.bindTo(this)
    }
}