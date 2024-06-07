package com.mmag.poiapp.data

sealed class POIAppResponse<T>(
    val data: T? = null,
    val message: String? = null,
    val code: Int? = null
) {
    class Success<T>(data: T?) : POIAppResponse<T>(data)

    class Error<T>(message: String?, data: T? = null, code: Int? = null) :
        POIAppResponse<T>(data, message, code)

    class Loading<T> : POIAppResponse<T>()
}