package devtee.com.photos

import devtee.com.photos.di.ApplicationComponent
import devtee.com.photos.di.applyAutoInjector
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import devtee.com.photos.di.DaggerApplicationComponent

class PhotosApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        applyAutoInjector()
    }

    override fun applicationInjector(): AndroidInjector<DaggerApplication> {
        val component: ApplicationComponent = DaggerApplicationComponent.builder().application(this).build()
        component.inject(this)

        return component as AndroidInjector<DaggerApplication>
    }
}