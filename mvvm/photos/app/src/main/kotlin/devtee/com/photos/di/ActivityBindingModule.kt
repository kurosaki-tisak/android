package devtee.com.photos.di

import devtee.com.photos.features.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity
}