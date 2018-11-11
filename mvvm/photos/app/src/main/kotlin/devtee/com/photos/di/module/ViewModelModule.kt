package devtee.com.photos.di.module

import android.arch.lifecycle.ViewModelProvider
import devtee.com.photos.di.util.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}