package com.mmag.poiapp.data.network

import com.google.gson.Gson
import com.mmag.poiapp.data.network.model.ErrorResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class NetworkSafeService {

    suspend fun <T> safeRequest(myRequest: suspend () -> Response<T>): NetworkResponse<T> {
        return withContext(Dispatchers.IO) {
            try {
                val apiResponse: Response<T> = myRequest()
                if (apiResponse.isSuccessful) {
                    NetworkResponse.Success(apiResponse.body())
                } else {
                    /**
                     * This type of error message could be changed to the server's error message object
                     */
                    val errorResponse = getErrorData(apiResponse.errorBody())
                    if (errorResponse != null) {
                        NetworkResponse.Error(errorResponse.errorMessage, null)
                    } else {
                        getGenericError(apiResponse)
                    }
                }
            } catch (e: HttpException) {
                NetworkResponse.Error(e.message() ?: "Something went wrong")
            } catch (e: IOException) {
                NetworkResponse.Error("Please check your network connection")
            } catch (e: Exception) {
                NetworkResponse.Error("Something went wrong")
            }
        }
    }

    private fun <T> getGenericError(apiResponse: Response<T>): NetworkResponse.Error<T> =
        when (apiResponse.code()) {
            in 299..300 -> {
                NetworkResponse.Error(
                    "Must redirect the request, redirection error",
                    null
                )
            }

            in 399..500 -> {
                when (apiResponse.code()) {
                    400 -> NetworkResponse.Error("Bad request")
                    401 -> NetworkResponse.Error("Unauthorized")
                    403 -> NetworkResponse.Error("Forbidden")
                    404 -> NetworkResponse.Error("Not Found")
                    else -> NetworkResponse.Error("Request error, code: ${apiResponse.code()}")
                }
            }

            in 499..600 -> {
                NetworkResponse.Error("Server error, code: ${apiResponse.code()}", null)
            }

            else -> {
                NetworkResponse.Error("Unknown error, code: ${apiResponse.code()}", null)
            }
        }

    private fun getErrorData(errorData: ResponseBody?): ErrorResponse? {
        try {
            return Gson().fromJson(errorData?.string(), ErrorResponse::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}