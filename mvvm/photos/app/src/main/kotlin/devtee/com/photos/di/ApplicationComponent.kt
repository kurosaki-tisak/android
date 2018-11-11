package devtee.com.photos.di

import android.app.Application
import devtee.com.photos.PhotosApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(dependencies = [], modules = [
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    ContextModule::class,
    ServiceModule::class,
    ActivityBindingModule::class,
    ViewModelModule::class])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: PhotosApplication)
}