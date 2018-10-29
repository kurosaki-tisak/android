package com.devtee.weather.di

import android.content.Context
import com.devtee.weather.service.location.WeatherLocationServices
import com.devtee.weather.service.location.WeatherLocationServicesImpl
import com.devtee.weather.service.resource.ResourceService
import com.devtee.weather.service.resource.ResourceServiceImpl
import com.devtee.weather.service.weather.WeatherAPI
import com.devtee.weather.service.weather.WeatherService
import com.devtee.weather.service.weather.WeatherServiceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class ServiceModule {

    @Singleton
    @Provides
    open fun providesWeatherService(weatherAPI: WeatherAPI): WeatherService = WeatherServiceImpl(weatherAPI)

    @Singleton
    @Provides
    open fun providesResourceService(context: Context): ResourceService = ResourceServiceImpl(context)

    @Singleton
    @Provides
    open fun providesLocationService(context: Context): WeatherLocationServices = WeatherLocationServicesImpl(context)
}