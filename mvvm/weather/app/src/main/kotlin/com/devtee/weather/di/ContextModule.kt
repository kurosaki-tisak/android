package com.devtee.weather.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
open class ContextModule(private val application: Application) {

    @Provides
    open fun provideContext(): Context = application.applicationContext

    @Provides
    open fun providesApplication(): Application = application
}