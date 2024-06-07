package com.mmag.poiapp.data.db.repository

import com.mmag.poiapp.data.db.POIDao
import com.mmag.poiapp.data.db.model.POIEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseRepositoryDefault @Inject constructor(
    private val poiDao: POIDao,
) : DatabaseRepository {
    override suspend fun addPOIList(list: List<POIEntity>) {
        poiDao.insertAllPOI(list)
    }

    override suspend fun getPOIList(): Flow<List<POIEntity>> = poiDao.getAllPOIs()
    override suspend fun getPOIsBySearchText(text: String): Flow<List<POIEntity>> =
        poiDao.getPOIsBySearchText(text)

    override suspend fun getPOIById(id: Int): Flow<POIEntity> = poiDao.getPOIById(id)
}