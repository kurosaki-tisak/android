package com.devtee.weather.service

import com.devtee.weather.coroutines.Android
import com.devtee.weather.service.base.ResponseType
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import okhttp3.Headers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.io.InterruptedIOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class DataTransferCallback<T>(private val success: (T?) -> Unit,
                              private val headers: ((Headers?) -> Unit)? = null,
                              private val fail: ((ResponseType, Throwable?) -> Unit)?) : Callback<T> {

    override fun onResponse(call: Call<T>, response: Response<T>) {
        headers?.invoke(response.headers())

        when (response.code()) {
            in 200..399 -> success.invoke(response.body())
            401 -> fail?.invoke(ResponseType.UNAUTHORIZED, null)
            503 -> fail?.invoke(ResponseType.EMPTY, null)
            502, 504 -> fail?.invoke(ResponseType.TIMEOUT, null)
            404 -> fail?.invoke(ResponseType.NOT_FOUND, null)

            else -> {
                fail?.invoke(ResponseType.GENERAL_ERROR, null)
            }
        }
    }

    override fun onFailure(call: Call<T>, throwable: Throwable) {
        if (throwable == null) {
            fail?.invoke(ResponseType.GENERAL_ERROR, throwable)
            return
        }
        when (throwable::class.java) {
            UnknownHostException::class.java -> fail?.invoke(ResponseType.NO_INTERNET, throwable)
            IOException::class.java -> fail?.invoke(ResponseType.NO_INTERNET, throwable)
            InterruptedIOException::class.java -> fail?.invoke(ResponseType.NO_INTERNET, throwable)
            SocketTimeoutException::class.java -> fail?.invoke(ResponseType.TIMEOUT, throwable)
            else -> fail?.invoke(ResponseType.GENERAL_ERROR, throwable)
        }
   }
}

fun <T> Call<T>.enqueue(success: (T?) -> Unit,
                        headers: ((Headers?) -> Unit)? = null,
                        fail: ((ResponseType, Throwable?) -> Unit)?) {

        enqueue(DataTransferCallback<T>(success, headers, fail))
}

fun <T, O> Call<T>.enqueueWithProcessing(preProcessing: (T?) -> O,
                                         success: (O?) -> Unit,
                                         headers: ((Headers?) -> Unit)? = null,
                                         fail: (ResponseType, Throwable?) -> Unit) {

    fun backgroundProcessing(obj: T?): Deferred<O> {
        return async(CommonPool) {
            return@async preProcessing(obj)
        }
    }

    val wrappedSuccess: (T?) -> Unit = {
        launch(Android) {
            val obj = backgroundProcessing(it).await()
            success.invoke(obj)
        }
    }

    enqueue(wrappedSuccess, headers, fail)
}