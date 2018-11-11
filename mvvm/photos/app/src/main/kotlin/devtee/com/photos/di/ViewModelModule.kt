package devtee.com.photos.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import devtee.com.photos.di.util.ViewModelFactory
import devtee.com.photos.di.util.ViewModelKey
import devtee.com.photos.features.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}