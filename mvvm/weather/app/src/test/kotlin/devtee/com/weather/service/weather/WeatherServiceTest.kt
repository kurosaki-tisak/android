package devtee.com.weather.service.weather

import android.app.Application
import com.devtee.weather.di.*
import com.devtee.weather.service.base.ResponseType
import com.devtee.weather.service.weather.WeatherAPI
import com.devtee.weather.service.weather.WeatherService
import com.devtee.weather.service.weather.WeatherServiceImpl
import com.nhaarman.mockito_kotlin.mock
import it.cosenonjaviste.daggermock.DaggerMockRule
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class WeatherServiceTest {

    @Mock
    val application = mock<Application>()

    @JvmField
    @Rule
    var mockitoRule = DaggerMockRule(
        ApplicationComponent::class.java,
        ApisModule(),
        ContextModule(application),
        NetworkModule(),
        RepositoryModule(),
        ServiceModule()
    )

    @Mock
    val weatherApi = mock<WeatherAPI>()

    private lateinit var weatherService: WeatherService

    @Before
    @Throws(Exception::class)
    fun setup() {
        MockitoAnnotations.initMocks(this)

        weatherService = WeatherServiceImpl(weatherApi)
    }

    @Test
    fun `error empty response test`() {
        assertEquals(ResponseType.EMPTY, weatherService.fetchCurrentWeather(0.0, 0.0, 0))
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {

    }
}