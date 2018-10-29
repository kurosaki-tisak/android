package com.devtee.weather.common.extensions

import android.databinding.Observable
import android.databinding.ObservableField

fun <T> ObservableField<T>.onValueChange(callback: (T) -> Unit) {
    this.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(p0: Observable?, p1: Int) {
            this@onValueChange.get()?.let { callback.invoke(it) }
        }
    })
}
