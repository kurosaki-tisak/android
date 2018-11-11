package devtee.com.photos.features

import android.databinding.DataBindingUtil
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import devtee.com.photos.R
import devtee.com.photos.databinding.ActivityMainBinding
import devtee.com.photos.di.util.ViewModelFactory
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main) }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binder = MainBinder(this, binding, viewModelFactory)
        binder.bindTo(this)
    }
}