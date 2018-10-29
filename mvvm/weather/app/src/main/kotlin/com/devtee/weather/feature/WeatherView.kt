package com.devtee.weather.feature

import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.ViewTreeObserver
import com.devtee.weather.R
import com.devtee.weather.common.extensions.slideUp
import com.devtee.weather.databinding.ActivityWeatherBinding
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration

interface WeatherView {
    fun setItems(items: List<ForecastItem>?)
}

class WeatherViewImpl(activity: AppCompatActivity,
                      private val binding: ActivityWeatherBinding) : WeatherView {

    private val listAdapter = WeatherListAdapter()

    init {
        with(binding.recyclerview) {
            val drawable = ContextCompat.getDrawable(context, R.drawable.decor_line)
            addItemDecoration(DividerItemDecoration(drawable))

            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
    }

    override fun setItems(items: List<ForecastItem>?) {
        listAdapter.items = items ?: emptyList()

        binding.recyclerview.viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutCallback())
    }

    private fun onGlobalLayoutCallback() = object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            binding.bottomSheet.slideUp(800)
            binding.recyclerview.viewTreeObserver.removeOnGlobalLayoutListener(this)
        }
    }
}