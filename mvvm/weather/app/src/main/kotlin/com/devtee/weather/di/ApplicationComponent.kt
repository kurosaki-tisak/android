package com.devtee.weather.di

import com.devtee.weather.feature.WeatherRepositoryImpl
import com.devtee.weather.feature.WeatherViewModel
import com.devtee.weather.service.location.WeatherLocationServices
import com.devtee.weather.service.resource.ResourceService
import com.devtee.weather.service.weather.WeatherAPI
import com.devtee.weather.service.weather.WeatherService
import com.google.gson.Gson
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ ContextModule::class, NetworkModule::class, ApisModule::class, ServiceModule::class, RepositoryModule::class ] )
interface ApplicationComponent {

    fun getGson(): Gson
    fun getWeatherAPI(): WeatherAPI
    fun getResourceService(): ResourceService
    fun getLocationService(): WeatherLocationServices

    fun inject(weatherService: WeatherService)
    fun inject(weatherRepository: WeatherRepositoryImpl)
    fun inject(weatherViewModel: WeatherViewModel)
}