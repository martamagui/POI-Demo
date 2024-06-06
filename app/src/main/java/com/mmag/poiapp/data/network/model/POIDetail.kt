package com.mmag.poiapp.data.network.model


import com.google.gson.annotations.SerializedName

data class POIDetail(
    @SerializedName("geocoordinates")
    val geocoordinates: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("title")
    val title: String
)