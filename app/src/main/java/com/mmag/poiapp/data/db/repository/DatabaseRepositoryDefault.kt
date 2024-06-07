package com.mmag.poiapp.data.db.repository

import com.mmag.poiapp.data.db.POIDao
import com.mmag.poiapp.data.db.model.POIEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DatabaseRepositoryDefault @Inject constructor(
    private val poiDao: POIDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : DatabaseRepository {

    override suspend fun addPOIList(list: List<POIEntity>) {
        withContext(dispatcher) { poiDao.insertAllPOI(list) }
    }

    override suspend fun getPOIList(): Flow<List<POIEntity>> = poiDao.getAllPOIs()

    override suspend fun getPOIsBySearchText(text: String): Flow<List<POIEntity>> =
        poiDao.getPOIsBySearchText(text)

    override suspend fun getPOIById(id: Int): Flow<POIEntity> = poiDao.getPOIById(id)
}