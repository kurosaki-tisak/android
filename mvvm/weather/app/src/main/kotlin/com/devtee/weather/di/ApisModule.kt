package com.devtee.weather.di

import com.devtee.weather.service.weather.WeatherAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
open class ApisModule {

    @Singleton
    @Provides
    open fun provideswWatherAPI(retrofit: Retrofit): WeatherAPI = retrofit.create(WeatherAPI::class.java)
}