package com.devtee.weather.common

import android.databinding.BindingAdapter
import android.view.View

@BindingAdapter("show")
fun showOrGoneView(view: View, show: Boolean) {
    when (show) {
        true -> view.visibility = View.VISIBLE
        false -> view.visibility = View.GONE
    }
}

@BindingAdapter("hidden")
fun showOrHideView(view: View, show: Boolean) {
    when (show) {
        true -> view.visibility = View.VISIBLE
        false -> view.visibility = View.INVISIBLE
    }
}
