package com.devtee.weather

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import com.devtee.weather.di.*
import net.danlew.android.joda.JodaTimeAndroid

open class WeatherApplication : Application() {

    companion object {
        @JvmStatic
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this)

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        applicationComponent = DaggerApplicationComponent.builder()
            .apisModule(ApisModule())
            .contextModule(ContextModule(this))
            .networkModule(NetworkModule())
            .serviceModule(ServiceModule())
            .repositoryModule(RepositoryModule())
            .build()
    }
}