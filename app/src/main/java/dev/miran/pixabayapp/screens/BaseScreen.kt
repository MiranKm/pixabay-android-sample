package dev.miran.pixabayapp.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.miran.pixabayapp.uiUtil.navigation.NavigationDestinations
import dev.miran.view_model.HomeViewModel

@Composable
fun BaseScreen() {
    val navHostController = rememberNavController()

    val homeViewModel:HomeViewModel = viewModel()
    NavHost(navHostController, NavigationDestinations.SearchScreen.route) {
        composable(NavigationDestinations.SearchScreen.route) {
            SearchScreen(homeViewModel,navHostController)
        }
        composable(
            NavigationDestinations.ImageDetailsScreen.route, arguments = listOf(
                navArgument("id") { type = NavType.IntType },
            )
        ) {
            val id = it.arguments?.getInt("id") ?: 0
            ImageDetailsScreen(id, navHostController)
        }

    }

}