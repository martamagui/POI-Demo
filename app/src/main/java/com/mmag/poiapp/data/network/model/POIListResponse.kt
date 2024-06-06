package com.mmag.poiapp.data.network.model


import com.google.gson.annotations.SerializedName

data class POIListResponse(
    @SerializedName("list")
    val list: List<POIDetail>
)