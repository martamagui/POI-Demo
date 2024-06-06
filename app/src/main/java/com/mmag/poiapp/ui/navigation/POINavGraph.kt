package com.mmag.poiapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mmag.poiapp.ui.screens.detail_screen.DetailScreen
import com.mmag.poiapp.ui.screens.home_screen.HomeScreen

@Composable
fun POINavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = NavigationConfig.HOME_ROUTE
    ) {
        composable(NavigationConfig.HOME_ROUTE) {
            HomeScreen()
        }

        composable(NavigationConfig.DETAIL_ROUTE,
            arguments = listOf(
                navArgument(NavigationConfig.Args.DETAIL_ARG) { type = NavType.IntType }
            )
        ) {
            val id = it.arguments?.getInt(NavigationConfig.Args.DETAIL_ARG)
            DetailScreen(poiId=id)
        }

    }
}