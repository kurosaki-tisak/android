package devtee.com.photos.features

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import devtee.com.photos.common.CommonBinder
import devtee.com.photos.databinding.ActivityMainBinding
import devtee.com.photos.di.util.ViewModelFactory

class MainBinder(activity: AppCompatActivity,
                 binding: ActivityMainBinding,
                 viewModelFactory: ViewModelFactory
) : CommonBinder {

    private val view = MainViewImpl(binding)
    private val viewModel: MainViewModel = ViewModelProviders.of(activity, viewModelFactory).get(MainViewModel::class.java)

    init {
        binding.viewModel = viewModel
    }

    override fun bindTo(owner: LifecycleOwner) {
        viewModel.isLoading().observe(owner, Observer { doNothing() })
        viewModel.albumList.observe(owner, Observer { view.setItems(it) })
        viewModel.error().observe(owner, Observer { view.showBottomSheet(it!!) })
        viewModel.onShowErrorMsg.observe(owner, Observer { view.showBottomSheet(it!!) })
    }

    private fun doNothing() {}
}