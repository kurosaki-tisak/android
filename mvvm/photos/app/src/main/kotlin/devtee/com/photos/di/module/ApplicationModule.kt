package devtee.com.photos.di.module

import dagger.Module
import dagger.Provides
import devtee.com.photos.BuildConfig
import devtee.com.photos.repository.PhotosService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun providesRetrofit() = Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun providesPhotosService(retrofit: Retrofit) = retrofit.create(PhotosService::class.java)
}