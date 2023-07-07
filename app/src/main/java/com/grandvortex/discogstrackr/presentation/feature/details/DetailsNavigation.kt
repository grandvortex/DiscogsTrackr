package com.grandvortex.discogstrackr.presentation.feature.details

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val ID = "id"
const val DETAILS_ROUTE = "details"

fun NavGraphBuilder.detailsScreen() {
    composable(
        route = "$DETAILS_ROUTE/{$ID}",
        arguments = listOf(navArgument(ID) { type = NavType.IntType })
    ) { navBackStackEntry ->
        val id = navBackStackEntry.arguments?.getInt(ID) ?: 0
        DetailsRoute(id)
    }
}

fun NavController.navigateToDetailsScreen(id: Int) {
    navigate("$DETAILS_ROUTE/$id")
}
