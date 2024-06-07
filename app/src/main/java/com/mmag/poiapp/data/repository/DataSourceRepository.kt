package com.mmag.poiapp.data.repository

import com.mmag.poiapp.data.POIAppResponse
import com.mmag.poiapp.data.model.POIDetail
import kotlinx.coroutines.flow.Flow

interface DataSourceRepository {
    suspend fun getAllPoi(): Flow<POIAppResponse<List<POIDetail>>>
    suspend fun getPoiBySearch(text: String): Flow<POIAppResponse<List<POIDetail>>>
    suspend fun getPoiById(id: String): Flow<POIAppResponse<POIDetail>>
}