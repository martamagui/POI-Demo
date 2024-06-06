package com.mmag.poiapp.data.network

sealed class NetworkResponse<T>(
    val data: T? = null,
    val message: String? = null,
    val code: Int? = null
) {
    class Success<T>(data: T?) : NetworkResponse<T>(data)

    class Error<T>(message: String?, data: T? = null, code: Int? = null) :
        NetworkResponse<T>(data, message, code)

    class Loading<T> : NetworkResponse<T>()
}