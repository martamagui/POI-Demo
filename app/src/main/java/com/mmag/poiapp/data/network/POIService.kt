package com.mmag.poiapp.data.network

import com.mmag.poiapp.BuildConfig
import com.mmag.poiapp.data.network.model.POIListResponse
import retrofit2.Response
import retrofit2.http.GET

interface POIService {
    @GET(BuildConfig.POI_WS)
    suspend fun getPoiList(): Response<POIListResponse>
}