package com.mmag.poiapp.data.network.model


import com.google.gson.annotations.SerializedName

data class POIDetailResponse(
    @SerializedName("geocoordinates")
    val geocoordinates: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("title")
    val title: String
)