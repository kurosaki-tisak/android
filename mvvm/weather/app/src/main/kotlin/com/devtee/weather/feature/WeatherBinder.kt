package com.devtee.weather.feature

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import com.devtee.weather.common.Binder
import com.devtee.weather.common.viewModel
import com.devtee.weather.databinding.ActivityWeatherBinding

class WeatherBinder(activity: AppCompatActivity,
                    binding: ActivityWeatherBinding,
                    repository: WeatherRepository): Binder {

    private val view: WeatherView
    private val viewModel: WeatherViewModel = activity.viewModel { WeatherViewModel(repository) }

    init {
        binding.viewModel = viewModel
        view = WeatherViewImpl(activity, binding)
    }

    override fun bindTo(owner: LifecycleOwner) {
        viewModel.location.observe(owner, Observer { doNothing() })
        viewModel.weatherData.observe(owner, Observer { doNothing() })
        viewModel.itemsList.observe(owner, Observer { view.setItems(it) })
    }

    private fun doNothing() {}
}