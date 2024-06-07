package com.mmag.poiapp.ui.screens.detail_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mmag.poiapp.R
import com.mmag.poiapp.data.POIAppResponse
import com.mmag.poiapp.ui.screens.detail_screen.components.DetailAppBar
import com.mmag.poiapp.ui.screens.detail_screen.components.DetailContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    poiId: Int?,
    navController: NavController,
    viewModel: DetailScreenViewModel = hiltViewModel(),
) {
    val poiState by viewModel.state.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(poiId) {
        viewModel.getDetailData(poiId)
    }

    Scaffold(
        topBar = { DetailAppBar(navController) },
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (poiState) {
                is POIAppResponse.Error -> Text(
                    text = poiState.message ?: stringResource(id = R.string.detail_error_default)
                )

                is POIAppResponse.Loading -> CircularProgressIndicator()
                is POIAppResponse.Success -> {
                    if (poiState.data != null) {
                        DetailContent(
                            poiState.data!!,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 24.dp, vertical = 32.dp)
                        )
                    }
                }
            }
        }
    }
}



