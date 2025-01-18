package com.brandon.oakchallenge.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.brandon.oakchallenge.ui.screen.HomeScreen
import com.brandon.oakchallenge.ui.screen.ListScreen
import com.brandon.oakchallenge.viewmodel.ProductViewModel

@Composable
fun AppNavigation(innerPadding: PaddingValues) {
    val navController = rememberNavController()
    val viewModel: ProductViewModel = viewModel()

    NavHost(navController = navController, startDestination = (Routes.HomeScreen.route)) {
        composable(Routes.HomeScreen.route) {
            HomeScreen(innerPadding, navController = navController, viewModel = viewModel)
        }

        composable(Routes.ListScreen.route) {
            ListScreen(innerPadding, navController = navController, viewModel = viewModel)
        }
    }

}