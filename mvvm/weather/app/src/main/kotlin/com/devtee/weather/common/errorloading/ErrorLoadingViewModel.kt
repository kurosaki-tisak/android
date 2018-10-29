package com.devtee.weather.common.errorloading

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.view.View
import com.devtee.weather.R
import com.devtee.weather.common.map
import com.devtee.weather.service.base.ResponseType
import com.devtee.weather.service.base.ResponseType.*
import com.devtee.weather.service.resource.ResourceService

open class ErrorLoadingViewModel<T>(repository: ErrorLoadingRepository<T>,
                                    private val resService: ResourceService) : ViewModel() {

    val loading = ObservableField<Boolean>(true)
    val showError = ObservableField<Boolean>(false)
    val showRetry = ObservableField<Boolean>(true)
    val error = ObservableField<String>()
    val retryListener = View.OnClickListener { repository.retry(); resetLoadingState() }

    val dataMediator: LiveData<Unit> = repository.getData().map(this::changeLoading)
    val errorsMediator: LiveData<Unit> = repository.getErrors().map(this::showError)

    private fun showError(errorMsg: ResponseType?) {
        showError.set(false)

        if (errorMsg == null) return

        when (errorMsg) {
            NO_INTERNET -> {
                error.set(resService.getString(R.string.common_text_nointernet))
                showRetry.set(true)
            }
            TIMEOUT -> {
                error.set(resService.getString(R.string.common_text_serverunreachable))
                showRetry.set(true)
            }
            UNAUTHORIZED -> {
                error.set(resService.getString(R.string.common_text_error_unauthorized))
                showRetry.set(false)
            }
            NOT_FOUND -> {
                error.set(resService.getString(R.string.common_text_screennotfound))
                showRetry.set(false)
            }
            EMPTY -> {
                error.set(resService.getString(R.string.common_text_empty))
                showRetry.set(false)
            }
            else -> {
                error.set(resService.getString(R.string.common_text_screennotfound))
                showRetry.set(true)
            }
        }

        loading.set(false)
        showError.set(true)
    }

    private fun resetLoadingState() {
        loading.set(true)
        showError.set(false)
        showRetry.set(false)
    }

    open fun changeLoading(data: T?) {
        showError.set(false)
        val isLoading = data == null
        loading.set(isLoading)
    }
}