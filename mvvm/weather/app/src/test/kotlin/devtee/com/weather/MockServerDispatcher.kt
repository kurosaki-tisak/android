package devtee.com.weather

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

internal class MockServerDispatcher {
    /**
     * Return ok response from mock server
     */
    internal inner class RequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when {
                request.path == "api/data" -> MockResponse().setResponseCode(200).setBody("{data:FakeData}")
                request.path == "api/codes" -> MockResponse().setResponseCode(200).setBody("{codes:FakeCode}")
                request.path == "api/number" -> MockResponse().setResponseCode(200).setBody("number:FakeNumber")
                else -> MockResponse().setResponseCode(404)
            }
        }
    }

    /**
     * Return error response from mock server
     */
    internal inner class ErrorDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return MockResponse().setResponseCode(400)
        }
    }
}