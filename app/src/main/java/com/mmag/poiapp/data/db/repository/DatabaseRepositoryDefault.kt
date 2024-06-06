package com.mmag.poiapp.data.db.repository

import com.mmag.poiapp.data.db.POIDao
import com.mmag.poiapp.data.db.model.POIEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseRepositoryDefault @Inject constructor(
    private val poiDao: POIDao
) :
    DatabaseRepository {
    override suspend fun addPOIList(list: List<POIEntity>) {
        TODO("Not yet implemented")
    }

    override suspend fun getPOIList(): Flow<List<POIEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPOIById(): Flow<POIEntity> {
        TODO("Not yet implemented")
    }
}