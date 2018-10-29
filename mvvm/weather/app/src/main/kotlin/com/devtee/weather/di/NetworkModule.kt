package com.devtee.weather.di

import com.devtee.weather.BuildConfig
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS
import okhttp3.logging.HttpLoggingInterceptor.Logger.DEFAULT
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
open class NetworkModule {

    @Provides
    @Singleton
    open fun provideRetrofit(gson: Gson) = Retrofit
        .Builder()
        .baseUrl("http://api.apixu.com/v1/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(OkHttpClient.Builder().addInterceptor(log()).build())
        .build()

    @Singleton
    @Provides
    open fun createGson() = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        .create()

    private fun log(): Interceptor {
        val logger = HttpLoggingInterceptor.Logger { message ->
            if (BuildConfig.DEBUG) DEFAULT.log(message)

        }

        val logInterceptor = HttpLoggingInterceptor(logger)
        logInterceptor.level = if (BuildConfig.DEBUG) BODY else HEADERS

        return logInterceptor
    }
}