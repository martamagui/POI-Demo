package com.mmag.poiapp.data.network.repository

import com.mmag.poiapp.data.network.NetworkResponse
import com.mmag.poiapp.data.network.model.POIListResponse

interface NetworkRepository {
    suspend fun getPOIList(): NetworkResponse<POIListResponse>
}