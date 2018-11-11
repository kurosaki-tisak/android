package devtee.com.photos.di

import android.content.Context
import devtee.com.photos.services.ResourceService
import devtee.com.photos.services.ResourceServiceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ServiceModule {

    @Singleton
    @Provides
    fun provideResourceService(context: Context): ResourceService = ResourceServiceImpl(context)
}