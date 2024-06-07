package com.mmag.poiapp.ui.screens.home_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmag.poiapp.data.network.repository.NetworkRepository
import com.mmag.poiapp.data.repository.DataSourceRepositoryDefault
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel@Inject constructor(
    private val dataSourceRepositoryDefault: DataSourceRepositoryDefault
) : ViewModel() {

    init {

    }
}