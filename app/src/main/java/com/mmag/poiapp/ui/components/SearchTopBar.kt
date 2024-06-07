package com.mmag.poiapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mmag.poiapp.R
import com.mmag.poiapp.ui.screens.home_screen.HomeScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopAppBar(
    title: String,
    viewModel: HomeScreenViewModel,
    modifier: Modifier = Modifier
) {

    val searchText by viewModel.searchText.collectAsState()

    TopAppBar(
        title = {
            Text(text = title)
        },
        actions = {
            TextField(
                value = searchText,
                onValueChange = {
                    viewModel.updateSearchText(it)
                    viewModel.searchPOI(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical =8.dp),
                placeholder = {
                    Text(text = stringResource(id = R.string.search_place_holder))
                },
                singleLine = true,
                maxLines = 1
            )
        },
        modifier = modifier
    )
}