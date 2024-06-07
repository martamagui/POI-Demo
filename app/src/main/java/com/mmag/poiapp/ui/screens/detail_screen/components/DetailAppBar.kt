package com.mmag.poiapp.ui.screens.detail_screen.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.mmag.poiapp.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DetailAppBar(navController: NavController) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.detail_title)) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = stringResource(id = R.string.detail_arrow_back_description)
                )
            }
        })
}