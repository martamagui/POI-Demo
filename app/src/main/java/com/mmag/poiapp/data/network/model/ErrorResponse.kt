package com.mmag.poiapp.data.network.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("error_message")
    val errorMessage: String
)
