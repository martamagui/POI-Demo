package com.mmag.poiapp.ui.screens.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.enterAlwaysScrollBehavior
import androidx.compose.runtime.Composable
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
import com.mmag.poiapp.ui.screens.home_screen.components.SearchTopAppBar
import com.mmag.poiapp.ui.screens.home_screen.components.HomeContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {

    val poiState by viewModel.state.collectAsState()
    val scrollBehavior = enterAlwaysScrollBehavior()

    Scaffold(
        topBar = {
            SearchTopAppBar(
                stringResource(id = R.string.home_screen_title),
                viewModel,
                scrollBehavior = scrollBehavior
            )
        },
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
                    text = poiState.message ?: stringResource(id = R.string.home_error_default)
                )

                is POIAppResponse.Loading -> CircularProgressIndicator()
                is POIAppResponse.Success -> if (poiState.data != null) {
                    HomeContent(poiState, navController)
                }

            }
        }
    }
}


