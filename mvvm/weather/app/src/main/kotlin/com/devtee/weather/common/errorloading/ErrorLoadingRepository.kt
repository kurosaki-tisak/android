package com.devtee.weather.common.errorloading

import android.arch.lifecycle.LiveData
import com.devtee.weather.common.map
import com.devtee.weather.service.base.ResponseType

interface ErrorLoadingRepository<T> {
    fun getErrors(): LiveData<ResponseType>
    fun getData(): LiveData<T>
    fun isEmpty(): LiveData<Boolean> = getData().map { data -> (data is List<*> && data.isEmpty()) }
    fun retry()
}