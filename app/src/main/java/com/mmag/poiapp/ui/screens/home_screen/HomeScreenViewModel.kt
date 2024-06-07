package com.mmag.poiapp.ui.screens.home_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmag.poiapp.data.POIAppResponse
import com.mmag.poiapp.data.model.POIDetail
import com.mmag.poiapp.data.network.repository.NetworkRepository
import com.mmag.poiapp.data.repository.DataSourceRepository
import com.mmag.poiapp.data.repository.DataSourceRepositoryDefault
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val dataSourceRepository: DataSourceRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<POIAppResponse<List<POIDetail>>> =
        MutableStateFlow(POIAppResponse.Loading())
    val state: StateFlow<POIAppResponse<List<POIDetail>>> get() = _state

    private val _searchText: MutableStateFlow<String> = MutableStateFlow("")
    val searchText: StateFlow<String> get() = _searchText

    init {
        getAllPOI()
    }

    //region DB
    private fun getAllPOI() {
        viewModelScope.launch(Dispatchers.IO) {
            dataSourceRepository.getAllPoi().collect { data ->
                _state.update { data }
            }
        }
    }

    fun searchPOI(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataSourceRepository.getPoiBySearch(text).collect { data ->
                _state.update { data }
            }
        }
    }
    //endregion DB

    //region State setters
    fun updateSearchText(text: String) {
        _searchText.update { text }
    }
    //endregion State setters
}