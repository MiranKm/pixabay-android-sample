package dev.miran.pixabayapp.uiUtil.navigation

import androidx.navigation.NavController

sealed class NavigationDestinations(val route: String, val params: String) {
    object SearchScreen : NavigationDestinations("search-screen", "")
    object ImageDetailsScreen : NavigationDestinations("image-details-screen", "{id}") {
        fun navigate(id: Int): String {
            return "$route/$id"
        }

        fun routeWithParam(): String {
            return "$route/$params"
        }
    }
}

fun NavController.navigateToImageDetailsScreen(id: Int) {
    navigate(NavigationDestinations.ImageDetailsScreen.navigate(id))
}