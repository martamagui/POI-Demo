package com.mmag.poiapp.ui.screens.detail_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmag.poiapp.data.POIAppResponse
import com.mmag.poiapp.data.model.POIDetail
import com.mmag.poiapp.data.repository.DataSourceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val dataSourceRepository: DataSourceRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<POIAppResponse<POIDetail>> =
        MutableStateFlow(POIAppResponse.Loading())
    val state: StateFlow<POIAppResponse<POIDetail>> get() = _state

    //region DB
    fun getDetailData(id: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            dataSourceRepository.getPoiById(id).collect { poi ->
                _state.update { poi }
            }
        }
    }
    //endregion DB
}