package com.grandvortex.discogstrackr.presentation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.grandvortex.discogstrackr.R

// Destination Routes
object NavDestinations {
    const val SEARCH_ROUTE = "search"
    const val FAVORITES_ROUTE = "favorites"
}

// Screens
sealed class Screen(val route: String, @StringRes val label: Int, val icon: ImageVector) {
    object Search : Screen(NavDestinations.SEARCH_ROUTE, R.string.label_search, Icons.Filled.Search)
    object Favorites :
        Screen(NavDestinations.FAVORITES_ROUTE, R.string.label_favorites, Icons.Rounded.Favorite)
}

// List of screens for bottom nav bar
val bottomNavList = listOf(
    Screen.Search,
    Screen.Favorites
)

class NavigationActions(navController: NavController) {
    private val navigateToSearch: () -> Unit = {
        navController.navigate(NavDestinations.SEARCH_ROUTE) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select itmes
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselection the same item
            launchSingleTop = true
            // Restore state when re-selecting a previously selected item
            restoreState = true
        }
    }

    private val navigateToFavorites: () -> Unit = {
        navController.navigate(NavDestinations.FAVORITES_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun getNavAction(screen: Screen): () -> Unit {
        return when (screen) {
            Screen.Search -> navigateToSearch
            Screen.Favorites -> navigateToFavorites
        }
    }
}
