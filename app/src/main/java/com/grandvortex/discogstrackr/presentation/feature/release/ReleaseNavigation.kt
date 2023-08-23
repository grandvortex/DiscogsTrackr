package com.grandvortex.discogstrackr.presentation.feature.release

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val RELEASE_ROUTE = "release"
const val RELEASE_ID_PARAM = "release_id" // Release id

fun NavGraphBuilder.releaseScreen(snackbarHostState: SnackbarHostState) {
    composable(
        route = "$RELEASE_ROUTE/{$RELEASE_ID_PARAM}",
        arguments = listOf(navArgument(RELEASE_ID_PARAM) { type = NavType.IntType })
    ) {
        ReleaseRoute(snackbarHostState = snackbarHostState)
    }
}

fun NavController.navigateToReleaseScreen(id: Int) {
    navigate(route = "$RELEASE_ROUTE/$id")
}