package com.grandvortex.discogstrackr.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.grandvortex.discogstrackr.R
import com.grandvortex.discogstrackr.feature.favorites.favoritesScreen
import com.grandvortex.discogstrackr.feature.favorites.navigateToFavoritesScreen
import com.grandvortex.discogstrackr.feature.search.navigateToSearchScreen
import com.grandvortex.discogstrackr.feature.search.searchScreen

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

fun navigateToScreen(navController: NavHostController, screen: Screen) {
    when (screen) {
        Screen.Search -> navController.navigateToSearchScreen()
        Screen.Favorites -> navController.navigateToFavoritesScreen()
    }
}

@Composable
fun DiscogsNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = NavDestinations.SEARCH_ROUTE,
        modifier = modifier
    ) {
        searchScreen()
        favoritesScreen()
    }
}
