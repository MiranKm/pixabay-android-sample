package dev.miran.pixabayapp.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.miran.pixabayapp.screens.detailsScreen.ImageDetailsScreen
import dev.miran.pixabayapp.screens.searchScreen.SearchScreen
import dev.miran.pixabayapp.uiUtil.navigation.NavigationDestinations

@Composable
fun BaseScreen() {
    val navHostController = rememberNavController()

    NavHost(navHostController, NavigationDestinations.SearchScreen.route) {
        composable(NavigationDestinations.SearchScreen.route) {
            SearchScreen(hiltViewModel(), navHostController)
        }
        composable(
            NavigationDestinations.ImageDetailsScreen.routeWithParam(), arguments = listOf(
                navArgument("id") { type = NavType.IntType },
            )
        ) {
            val id = it.arguments?.getInt("id") ?: 0
            ImageDetailsScreen(id, hiltViewModel(), navHostController)
        }

    }

}