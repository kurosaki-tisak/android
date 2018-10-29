package devtee.com.weather.service

import devtee.com.weather.MockServerDispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class DataTransferCallbackTest {

    private lateinit var webServer: MockWebServer

    @Before
    @Throws(Exception::class)
    fun setup() {
        webServer = MockWebServer()
        webServer.start(8080)
    }

    @Test
    fun simpleTest() {
        webServer.setDispatcher(MockServerDispatcher().RequestDispatcher())

        val response = MockResponse()
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .addHeader("Cache-Control", "no-cache")
            .setBody("{}")
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        webServer.shutdown()
    }
}