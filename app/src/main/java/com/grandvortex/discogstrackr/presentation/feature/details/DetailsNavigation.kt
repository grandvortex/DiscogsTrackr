package com.grandvortex.discogstrackr.presentation.feature.details

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val ID_PARAM = "id"
const val DETAILS_ROUTE = "details"

fun NavGraphBuilder.detailsScreen() {
    composable(
        route = "$DETAILS_ROUTE/{$ID_PARAM}",
        arguments = listOf(navArgument(ID_PARAM) { type = NavType.IntType })
    ) { navBackStackEntry ->
        val id = navBackStackEntry.arguments?.getInt(ID_PARAM) ?: -1
        DetailsRoute(id)
    }
}

fun NavController.navigateToDetailsScreen(id: Int) {
    navigate(route = "$DETAILS_ROUTE/$id")
}
