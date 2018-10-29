package com.devtee.weather.common.errorloading

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import com.devtee.weather.WeatherApplication
import com.devtee.weather.common.ErrorBinder
import com.devtee.weather.common.viewModel
import com.devtee.weather.databinding.PartialLoadingErrorBinding

class ErrorLoadingBinder<T>(activity: AppCompatActivity,
                            repository: ErrorLoadingRepository<T>,
                            binding: PartialLoadingErrorBinding) : ErrorBinder<T> {

    private var view: ErrorLoadingView
    private val resService = WeatherApplication.applicationComponent.getResourceService()
    private val viewModel: ErrorLoadingViewModel<T> = activity.viewModel { ErrorLoadingViewModel(repository, resService) }

    init {
        binding.viewModel = viewModel

        view = ErrorLoadingViewImpl(activity, binding)
    }

    override fun bindTo(owner: LifecycleOwner) {
        viewModel.dataMediator.observe(owner, Observer<Unit> { doNothing() })
        viewModel.errorsMediator.observe(owner, Observer<Unit> { doNothing() })
    }

    private fun doNothing() {}

    override fun getLoadingIndicator() = viewModel.loading
}