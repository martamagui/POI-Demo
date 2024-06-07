package com.mmag.poiapp.ui.screens.home_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
){
    Scaffold(
        topBar = {},
        modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(it)) {
            Text(text = "hola")
        }
    }
}