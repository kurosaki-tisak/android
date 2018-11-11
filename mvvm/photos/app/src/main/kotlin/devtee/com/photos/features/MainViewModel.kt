package devtee.com.photos.features

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.view.View
import devtee.com.photos.common.helper.TSSHelper
import devtee.com.photos.common.livedata.SingleLiveEvent
import devtee.com.photos.common.livedata.mapNotNull
import devtee.com.photos.model.Album
import devtee.com.photos.repository.PhotosRepository
import devtee.com.photos.services.ResourceService
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: PhotosRepository) : ViewModel() {

    @Inject
    lateinit var resourceService: ResourceService

    val loading = ObservableBoolean(true)
    val isShowRetryButton = ObservableBoolean(false)
    val onRetryClick = View.OnClickListener { retry() }
    val onShowErrorMsg = SingleLiveEvent<String>()

    val albumList: LiveData<List<Album>>
        get() = repository.getData()

    init {
        fetchData()
    }

    private fun retry() {
        isShowRetryButton.set(false)
        fetchData()
    }

    private fun fetchData() {
        repository.fetchAlbums()
    }

    fun isLoading() = repository.getLoading().mapNotNull { value ->
        when (value) {
            true -> loading.set(true)
            else -> loading.set(false)
        }
    }

    fun error(): LiveData<String> = repository.getError().mapNotNull { error ->
        when (error.isNullOrBlank()) {
            true -> isShowRetryButton.set(false)
            else -> {
                onShowErrorMsg.value = "Error $error"
                isShowRetryButton.set(true)
            }
        }
        return@mapNotNull error
    }

    fun textToSpeech(text: String?) {
        TSSHelper.getInstance(resourceService.context)
                .speak(text ?: "")
    }
}