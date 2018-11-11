package devtee.com.photos.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import devtee.com.photos.model.Album
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runner.RunWith
import org.junit.runners.model.Statement
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PhotosRepositoryTest {

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockPhotosService: PhotosService

    private lateinit var photosRepository: PhotosRepository

    private val LIST = ArrayList<Album>()
    private val EXCEPTION = Throwable("Error")

    @Before
    @Throws(Exception::class)
    fun setup() {
        MockitoAnnotations.initMocks(this)

        photosRepository = PhotosRepository(mockPhotosService)

        LIST.add(Album(1, 1, "Title 1", "URL 1", "URL 1"))
        LIST.add(Album(2, 2, "Title 2", "URL 2", "URL 2"))
    }

    @Test
    fun `get data from api`() {
        `when`(mockPhotosService.getPhotos()).thenReturn(Observable.just(LIST))

        photosRepository.fetchAlbums()

        assertEquals(LIST, photosRepository.getData().value)
    }

    @Test
    fun `stop loading after getting data from api has finish`() {
        `when`(mockPhotosService.getPhotos()).thenReturn(Observable.just(LIST))

        photosRepository.fetchAlbums()

        assertEquals(false, photosRepository.getLoading().value)
    }

    @Test
    fun `get error when something wrong with api call`() {
        `when`(mockPhotosService.getPhotos()).thenReturn(Observable.error<List<Album>>(EXCEPTION))

        photosRepository.fetchAlbums()

        assertEquals(EXCEPTION.message, photosRepository.getError().value)
    }
}

class RxImmediateSchedulerRule : TestRule {

    override fun apply(base: Statement, d: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
                RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
                RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
                RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

                try {
                    base.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                    RxAndroidPlugins.reset()
                }
            }
        }
    }
}