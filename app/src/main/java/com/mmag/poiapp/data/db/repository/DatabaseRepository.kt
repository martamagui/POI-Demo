package com.mmag.poiapp.data.db.repository

import com.mmag.poiapp.data.db.model.POIEntity
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {
    suspend fun addPOIList(list: List<POIEntity>)
    suspend fun getPOIList(): Flow<List<POIEntity>>
    suspend fun getPOIsBySearchText(text: String): Flow<List<POIEntity>>
    suspend fun getPOIById(id: Int): Flow<POIEntity>
}