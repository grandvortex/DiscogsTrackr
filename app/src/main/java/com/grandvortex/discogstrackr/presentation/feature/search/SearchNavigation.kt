package com.grandvortex.discogstrackr.presentation.feature.search

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val SEARCH_ROUTE = "search"

fun NavGraphBuilder.searchScreen(onClickItem: (Int) -> Unit) {
    composable(route = SEARCH_ROUTE) {
        SearchRoute(onClickItem = onClickItem)
    }
}

fun NavController.navigateToSearchScreen() {
    navigate(SEARCH_ROUTE) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselection the same item
        launchSingleTop = true
        // Restore state when re-selecting a previously selected item
        restoreState = true
    }
}
