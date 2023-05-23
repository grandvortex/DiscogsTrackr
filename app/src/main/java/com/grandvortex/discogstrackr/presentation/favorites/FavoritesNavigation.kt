package com.grandvortex.discogstrackr.presentation.favorites

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.grandvortex.discogstrackr.presentation.NavDestinations

fun NavGraphBuilder.favoritesScreen() {
    composable(route = NavDestinations.FAVORITES_ROUTE) {
        FavoritesScreen()
    }
}

fun NavController.navigateToFavoritesScreen() {
    navigate(NavDestinations.FAVORITES_ROUTE) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
