package com.devtee.weather.common

import android.arch.lifecycle.LifecycleOwner

interface Binder {
    fun bindTo(owner: LifecycleOwner)
}