package com.mmag.poiapp.ui.screens.home_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mmag.poiapp.data.POIAppResponse
import com.mmag.poiapp.data.model.POIDetail
import com.mmag.poiapp.ui.navigation.NavigationConfig


@Composable
fun HomeContent(
    poiState: POIAppResponse<List<POIDetail>>,
    navController: NavController,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .fillMaxSize()
    ) {
        items(poiState.data ?: listOf()) { poi ->
            POIiItem(
                poi = poi,
                onclick = {
                    val route = NavigationConfig.Builder.poiDetail(poi.id.toString())
                    navController.navigate(route)
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}