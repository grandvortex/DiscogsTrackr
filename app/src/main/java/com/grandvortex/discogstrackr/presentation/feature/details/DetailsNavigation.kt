package com.grandvortex.discogstrackr.presentation.feature.details

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val DETAILS_ROUTE = "details"
private const val ID_PARAM = "id" // Resource id
private const val TYPE_PARAM = "type" // Resource type

fun NavGraphBuilder.detailsScreen() {
    composable(
        route = "$DETAILS_ROUTE/{$TYPE_PARAM}/{$ID_PARAM}",
        arguments = listOf(
            navArgument(TYPE_PARAM) { type = NavType.StringType },
            navArgument(ID_PARAM) { type = NavType.IntType }
        )
    ) { navBackStackEntry ->
        val type = navBackStackEntry.arguments?.getString((TYPE_PARAM)) ?: ""
        val id = navBackStackEntry.arguments?.getInt(ID_PARAM) ?: -1
        DetailsRoute(type, id)
    }
}

fun NavController.navigateToDetailsScreen(type: String, id: Int) {
    navigate(route = "$DETAILS_ROUTE/$type/$id")
}
