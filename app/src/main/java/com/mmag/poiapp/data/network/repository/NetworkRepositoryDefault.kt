package com.mmag.poiapp.data.network.repository

import com.mmag.poiapp.data.network.NetworkResponse
import com.mmag.poiapp.data.network.NetworkSafeService
import com.mmag.poiapp.data.network.POIService
import com.mmag.poiapp.data.network.model.POIListResponse
import javax.inject.Inject

class NetworkRepositoryDefault @Inject constructor(
    private val service: POIService,
) : NetworkRepository, NetworkSafeService() {

    override suspend fun getPOIList(): NetworkResponse<POIListResponse> {
        return safeRequest { service.getPoiList() }
    }
}
