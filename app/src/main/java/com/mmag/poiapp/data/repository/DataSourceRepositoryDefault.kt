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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DataSourceRepositoryDefault @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val databaseRepository: DatabaseRepository,
) : DataSourceRepository {

    override suspend fun getAllPoi(): Flow<POIAppResponse<List<POIDetail>>> = flow {
        this.emit(POIAppResponse.Loading())
        databaseRepository.getPOIList().collect { list ->
            if (!list.isNullOrEmpty()) {
                val externalModelList = handleDatabaseResponse(list)
                this.emit(POIAppResponse.Success(externalModelList))
            } else {
                getPOIFromNetwork(this, this@DataSourceRepositoryDefault)
            }
        }
    }

    private suspend fun getPOIFromNetwork(
        flowCollector: FlowCollector<POIAppResponse<List<POIDetail>>>,
        dataSourceRepositoryDefault: DataSourceRepositoryDefault,
    ) {
        val networkResponse = networkRepository.getPOIList()
        when (networkResponse) {
            is NetworkResponse.Error -> flowCollector.emit(POIAppResponse.Error(networkResponse.message))
            is NetworkResponse.Loading -> {/*Keep loading*/
            }

            is NetworkResponse.Success -> {
                dataSourceRepositoryDefault.handleNetworkResponse(
                    networkResponse.data?.list ?: listOf()
                )
            }
        }
    }

    private suspend fun handleNetworkResponse(networkResponse: List<POIDetailResponse>) {
        val newList = mutableListOf<POIEntity>()
        networkResponse?.forEach { item -> newList.add(item.toPoiDetail().toEntity()) }
        databaseRepository.addPOIList(newList)
    }


    private fun handleDatabaseResponse(list: List<POIEntity>): MutableList<POIDetail> {
        val externalModelList = mutableListOf<POIDetail>()
        list.forEach { item -> externalModelList.add(item.toPoiDetail()) }
        return externalModelList
    }

    override suspend fun getPoiBySearch(text: String): Flow<POIAppResponse<List<POIDetail>>> =
        flow {
            this.emit(POIAppResponse.Loading())
            databaseRepository.getPOIsBySearchText(text).collect { list ->
                val externalModelList = handleDatabaseResponse(list)
                this.emit(POIAppResponse.Success(externalModelList))
            }
        }

    override suspend fun getPoiById(id: String): Flow<POIAppResponse<POIDetail>> = flow {
        this.emit(POIAppResponse.Loading())
        databaseRepository.getPOIById(id.toInt()).collect { poi ->
            if (poi != null) {
                this.emit(POIAppResponse.Success(poi.toPoiDetail()))
            } else {
                this.emit(POIAppResponse.Error(message = "Couldn't find POI."))
            }
        }
    }
}