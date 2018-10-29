package com.devtee.weather.common

import android.databinding.ObservableField

interface ErrorBinder<T> : Binder {
    fun getLoadingIndicator(): ObservableField<Boolean>
}