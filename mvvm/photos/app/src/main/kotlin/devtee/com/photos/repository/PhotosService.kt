package devtee.com.photos.repository

import devtee.com.photos.model.Album
import io.reactivex.Observable
import retrofit2.http.GET

interface PhotosService {

    @GET("photos/")
    fun getPhotos(): Observable<List<Album>>
}