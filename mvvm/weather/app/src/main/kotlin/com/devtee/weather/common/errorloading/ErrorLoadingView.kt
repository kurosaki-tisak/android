package com.devtee.weather.common.errorloading

import android.support.v7.app.AppCompatActivity
import android.view.animation.AnimationUtils
import com.devtee.weather.R
import com.devtee.weather.databinding.PartialLoadingErrorBinding

interface ErrorLoadingView {
    fun startAnimation()
}

class ErrorLoadingViewImpl(activity: AppCompatActivity,
                           private val binding: PartialLoadingErrorBinding): ErrorLoadingView {

    private val animation = AnimationUtils.loadAnimation(activity, R.anim.rotate)

    init {
        startAnimation()
    }

    override fun startAnimation() {
        binding.progress.startAnimation(animation)
    }
}