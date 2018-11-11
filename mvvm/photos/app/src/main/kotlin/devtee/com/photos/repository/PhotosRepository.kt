package devtee.com.photos.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import devtee.com.photos.model.Album
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PhotosRepository @Inject constructor(private val api: PhotosService) {

    private val disposable = CompositeDisposable()
    private val data = MutableLiveData<List<Album>>()
    private val error = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()

    fun fetchAlbums() {
        loading.value = true

        disposable.add(api.getPhotos().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object : DisposableObserver<List<Album>>() {

                    override fun onComplete() {
                        reset()
                    }

                    override fun onNext(value: List<Album>) {
                        data.value = value
                    }

                    override fun onError(e: Throwable) {
                        error.value = e.localizedMessage
                        loading.value = false
                    }
                }))
    }

    fun getLoading(): LiveData<Boolean> = loading
    fun getData(): LiveData<List<Album>> = data
    fun getError(): LiveData<String> = error

    private fun reset() {
        loading.value = false
        error.value = null
        disposable.clear()
    }
}