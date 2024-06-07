package com.mmag.poiapp.ui.screens.home_screen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mmag.poiapp.R
import com.mmag.poiapp.ui.screens.home_screen.HomeScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopAppBar(
    title: String,
    viewModel: HomeScreenViewModel,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    val searchText by viewModel.searchText.collectAsState()

    TopAppBar(
        title = {
            Text(text = title)
        },
        scrollBehavior = scrollBehavior,
        actions = {
            TextField(
                value = searchText,
                onValueChange = {
                    viewModel.updateSearchText(it)
                    viewModel.searchPOI(it)
                },

                placeholder = {
                    Text(text = stringResource(id = R.string.home_search_place_holder))
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp, horizontal = 12.dp),
            )
        },
        modifier = modifier
    )
}