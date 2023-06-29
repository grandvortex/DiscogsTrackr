package com.grandvortex.discogstrackr.presentation.navigation

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
import com.grandvortex.discogstrackr.presentation.feature.favorites.FAVORITES_ROUTE
import com.grandvortex.discogstrackr.presentation.feature.favorites.favoritesScreen
import com.grandvortex.discogstrackr.presentation.feature.favorites.navigateToFavoritesScreen
import com.grandvortex.discogstrackr.presentation.feature.search.SEARCH_ROUTE
import com.grandvortex.discogstrackr.presentation.feature.search.navigateToSearchScreen
import com.grandvortex.discogstrackr.presentation.feature.search.searchScreen

// Screens
sealed class Screen(val route: String, @StringRes val label: Int, val icon: ImageVector) {
    object Search : Screen(SEARCH_ROUTE, R.string.nav_label_search, Icons.Filled.Search)
    object Favorites :
        Screen(FAVORITES_ROUTE, R.string.nav_label_favorites, Icons.Rounded.Favorite)
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
        startDestination = SEARCH_ROUTE,
        modifier = modifier
    ) {
        searchScreen()
        favoritesScreen()
    }
}
