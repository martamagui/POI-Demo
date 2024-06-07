package com.mmag.poiapp.data.network.repository

import com.mmag.poiapp.data.network.NetworkResponse
import com.mmag.poiapp.data.network.NetworkSafeService
import com.mmag.poiapp.data.network.POIService
import com.mmag.poiapp.data.network.model.POIListResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkRepositoryDefault @Inject constructor(
    private val service: POIService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : NetworkRepository, NetworkSafeService() {

    override suspend fun getPOIList(): NetworkResponse<POIListResponse> = withContext(dispatcher) {
        return@withContext safeRequest { service.getPoiList() }
    }
}
