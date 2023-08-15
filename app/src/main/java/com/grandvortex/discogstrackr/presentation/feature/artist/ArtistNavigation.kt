package com.grandvortex.discogstrackr.presentation.feature.artist

import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val ARTIST_ROUTE = "artist"
const val ARTIST_ID_PARAM = "artist_id" // Artist id

fun NavGraphBuilder.artistScreen(modifier: Modifier, snackbarHostState: SnackbarHostState) {
    composable(
        route = "$ARTIST_ROUTE/{$ARTIST_ID_PARAM}",
        arguments = listOf(navArgument(ARTIST_ID_PARAM) { type = NavType.IntType })
    ) {
        ArtistRoute(
            modifier = modifier,
            snackbarHostState = snackbarHostState
        )
    }
}

fun NavController.navigateToArtistScreen(id: Int) {
    navigate(route = "$ARTIST_ROUTE/$id")
}
