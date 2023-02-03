package dev.miran.pixabayapp.uiUtil.navigation

import dev.miran.pixabayapp.uiUtil.navigation.NavigationDestinations.SearchScreen.route

sealed class NavigationDestinations(val route: String) {
    object SearchScreen : NavigationDestinations("search-screen")
    object ImageDetailsScreen : NavigationDestinations("image-details-screen/{id}"){
        fun navigate(id:Int):String{
            return "$route/$id"
        }
    }
}