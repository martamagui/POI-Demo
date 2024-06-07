package com.mmag.poiapp.data.repository

import com.mmag.poiapp.data.db.repository.DatabaseRepository
import com.mmag.poiapp.data.network.repository.NetworkRepository
import com.mmag.poiapp.data.POIAppResponse
import com.mmag.poiapp.data.db.model.POIEntity
import com.mmag.poiapp.data.model.POIDetail
import com.mmag.poiapp.data.model.toEntity
import com.mmag.poiapp.data.model.toPoiDetail
import com.mmag.poiapp.data.network.NetworkResponse
import com.mmag.poiapp.data.network.model.POIDetailResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataSourceRepositoryDefault @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val databaseRepository: DatabaseRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : DataSourceRepository {

    //region DB
    override suspend fun getAllPoi(): Flow<POIAppResponse<List<POIDetail>>> = flow {
        this.emit(POIAppResponse.Loading())
        databaseRepository.getPOIList().collect { list ->
            if (!list.isNullOrEmpty()) {
                val externalModelList = handlePoiListDatabaseResponse(list)
                this.emit(POIAppResponse.Success(externalModelList))
            } else {
                getPOIFromNetwork(this, this@DataSourceRepositoryDefault)
            }
        }
    }

    override suspend fun getPoiBySearch(text: String): Flow<POIAppResponse<List<POIDetail>>> =
        flow {
            this.emit(POIAppResponse.Loading())
            if (text.isNotEmpty()) {
                databaseRepository.getPOIsBySearchText(text).collect { list ->
                    val externalModelList = handlePoiListDatabaseResponse(list)
                    this.emit(POIAppResponse.Success(externalModelList))
                }
            } else {
                databaseRepository.getPOIList().collect { list ->
                    val externalModelList = handlePoiListDatabaseResponse(list)
                    this.emit(POIAppResponse.Success(externalModelList))
                }
            }
        }

    override suspend fun getPoiById(id: Int?): Flow<POIAppResponse<POIDetail>> = flow {
        this.emit(POIAppResponse.Loading())
        if (id != null) {
            databaseRepository.getPOIById(id).collect { poi ->
                if (poi != null) {
                    this.emit(POIAppResponse.Success(poi.toPoiDetail()))
                } else {
                    this.emit(POIAppResponse.Error(message = "Couldn't find POI."))
                }
            }
        } else {
            this.emit(POIAppResponse.Error(message = "Couldn't find POI."))
        }
    }

    private fun handlePoiListDatabaseResponse(list: List<POIEntity>): MutableList<POIDetail> {
        val externalModelList = mutableListOf<POIDetail>()
        list.forEach { item -> externalModelList.add(item.toPoiDetail()) }
        return externalModelList
    }
    //endregion DB

    //region NETWORK
    private suspend fun getPOIFromNetwork(
        flowCollector: FlowCollector<POIAppResponse<List<POIDetail>>>,
        dataSourceRepositoryDefault: DataSourceRepositoryDefault,
    ) = withContext(dispatcher) {
        val networkResponse = networkRepository.getPOIList()
        when (networkResponse) {
            is NetworkResponse.Error -> flowCollector.emit(POIAppResponse.Error(networkResponse.message))
            is NetworkResponse.Loading -> {/*Keep loading*/ }

            is NetworkResponse.Success -> {
                val data = networkResponse.data?.list ?: listOf()
                dataSourceRepositoryDefault.handleNetworkResponse(data)
            }
        }
    }

    private suspend fun handleNetworkResponse(networkResponse: List<POIDetailResponse>) =
        withContext(dispatcher) {
            val newList = mutableListOf<POIEntity>()
            networkResponse?.forEach { item -> newList.add(item.toPoiDetail().toEntity()) }
            databaseRepository.addPOIList(newList)
        }
    //endregion NETWORK
}