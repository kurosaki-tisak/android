package devtee.com.photos.features

import android.support.design.widget.Snackbar
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import devtee.com.photos.databinding.ActivityMainBinding
import devtee.com.photos.model.Album

interface MainView {
    fun setItems(items: List<Album>?)
    fun showBottomSheet(text: String)
}

class MainViewImpl(private val binding: ActivityMainBinding) : MainView {

    private var mainListAdapter: MainListAdapter

    init {
        with(binding.recyclerView) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(binding.root.context)
            ViewCompat.setNestedScrollingEnabled(binding.recyclerView, false)

            val item: (Album) -> Unit = { binding.viewModel?.textToSpeech(it.title) }
            mainListAdapter = MainListAdapter(item)
            adapter = mainListAdapter
        }
    }

    override fun setItems(items: List<Album>?) {
        (binding.recyclerView.adapter as MainListAdapter).items = items ?: emptyList()
    }

    override fun showBottomSheet(text: String) {
        Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT).show()
    }
}