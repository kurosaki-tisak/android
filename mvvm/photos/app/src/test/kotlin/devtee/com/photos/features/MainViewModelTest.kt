package devtee.com.photos.features

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import devtee.com.photos.model.Album
import devtee.com.photos.repository.PhotosRepository
import devtee.com.photos.repository.RxImmediateSchedulerRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class MainViewModelTest {

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockRepository: PhotosRepository

    @Mock
    lateinit var viewModel: MainViewModel

    @Mock
    lateinit var observerError: Observer<String>

    @Mock
    lateinit var observerBoolean: Observer<Unit>

    @Mock
    lateinit var observerList: Observer<List<Album>>

    private val LIST = ArrayList<Album>()

    private val liveData = MutableLiveData<List<Album>>()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        LIST.add(Album(1, 1, "Title 1", "URL 1", "URL 1"))
        LIST.add(Album(2, 2, "Title 2", "URL 2", "URL 2"))

        liveData.value = LIST

        viewModel = MainViewModel(mockRepository)
    }

    @Test
    fun `getting data from repo success`() {
        `when`(mockRepository.getData()).thenReturn(liveData)

        viewModel.albumList.observeForever(observerList)

        assertEquals(LIST, viewModel.albumList.value)
    }

    @Test
    fun `getting error from repo`() {
        val error = MutableLiveData<String>()
        error.value = "error"

        `when`(mockRepository.getError()).thenReturn(error)

        viewModel.error().observeForever(observerError)

        assertEquals("Error error", viewModel.onShowErrorMsg.value)
    }

    @Test
    fun `hide retry button after getting data api success`() {
        `when`(mockRepository.getData()).thenReturn(liveData)

        assertEquals(false, viewModel.isShowRetryButton.get())
    }

    @Test
    fun `show loading progress while getting data from api`() {
        val liveDataBoolean = MutableLiveData<Boolean>()
        liveDataBoolean.value = true

        `when`(mockRepository.getLoading()).thenReturn(liveDataBoolean)
        viewModel.isLoading().observeForever(observerBoolean)

        assertEquals(true, viewModel.loading.get())
    }

    @Test
    fun `hide loading progress after getting data from api completed`() {
        val liveDataBoolean = MutableLiveData<Boolean>()
        liveDataBoolean.value = false

        `when`(mockRepository.getLoading()).thenReturn(liveDataBoolean)
        viewModel.isLoading().observeForever(observerBoolean)

        assertEquals(false, viewModel.loading.get())
    }

    @Test
    fun `show retry button when get error from api`() {
        val error = MutableLiveData<String>()
        error.value = "error"

        `when`(mockRepository.getError()).thenReturn(error)

        viewModel.error().observeForever(observerError)

        assertEquals(true, viewModel.isShowRetryButton.get())
    }
}