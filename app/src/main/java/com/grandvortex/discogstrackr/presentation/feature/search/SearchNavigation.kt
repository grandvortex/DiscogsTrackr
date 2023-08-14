package com.grandvortex.discogstrackr.presentation.feature.search

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.grandvortex.discogstrackr.data.ResourceType
import com.grandvortex.discogstrackr.presentation.feature.artist.navigateToArtistScreen

const val SEARCH_ROUTE = "search"

fun NavGraphBuilder.searchScreen(
    navController: NavController,
    snackbarHostState: SnackbarHostState
) {
    val onClickItem = { type: ResourceType, id: Int ->
        when (type) {
            ResourceType.ARTIST -> {
                navController.navigateToArtistScreen(id)
            }

            ResourceType.LABEL -> {}
            ResourceType.RELEASE -> {}
            ResourceType.MASTER -> {}
            ResourceType.UNKNOWN -> {}
        }
    }

    composable(route = SEARCH_ROUTE) {
        SearchRoute(
            onClickItem = onClickItem,
            snackbarHostState = snackbarHostState
        )
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
