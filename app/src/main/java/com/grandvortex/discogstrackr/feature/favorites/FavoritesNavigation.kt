package com.grandvortex.discogstrackr.feature.favorites

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val FAVORITES_ROUTE = "favorites"

fun NavGraphBuilder.favoritesScreen() {
    composable(route = FAVORITES_ROUTE) {
        FavoritesScreen()
    }
}

fun NavController.navigateToFavoritesScreen() {
    navigate(FAVORITES_ROUTE) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
