package com.mmag.poiapp.ui.screens.home_screen

import android.util.Log
import android.widget.ProgressBar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.mmag.poiapp.R
import com.mmag.poiapp.data.POIAppResponse
import com.mmag.poiapp.ui.components.SearchTopAppBar

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {

    val poiState by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            SearchTopAppBar(
                stringResource(id = R.string.home_screen_title),
                viewModel
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            when (poiState) {
                is POIAppResponse.Error -> Text(text = poiState.message ?: "Unexpected error")
                is POIAppResponse.Loading -> CircularProgressIndicator()
                is POIAppResponse.Success -> {
                    if (poiState.data != null)
                        LazyColumn(
                            modifier = Modifier
                                .padding(paddingValues)
                                .fillMaxSize()
                        ) {
                            items(poiState.data?: listOf()) {
                                Text(text = it.title)
                            }
                        }
                }
            }
        }
    }
}