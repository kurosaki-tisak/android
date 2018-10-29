package com.devtee.weather.di

import com.devtee.weather.feature.WeatherRepository
import com.devtee.weather.feature.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class RepositoryModule {

    @Singleton
    @Provides
    open fun provideWeatherRepository(): WeatherRepository = WeatherRepositoryImpl()
}