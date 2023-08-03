package com.grandvortex.discogstrackr.presentation.feature.details

import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.grandvortex.discogstrackr.data.ResourceType

const val DETAILS_ROUTE = "details"
const val ID_PARAM = "id" // Resource id
const val TYPE_PARAM = "type" // ResourceType, one of MASTER,RELEASE,LABEL,ARTIST

fun NavGraphBuilder.detailsScreen(modifier: Modifier, snackbarHostState: SnackbarHostState) {
    composable(
        route = "$DETAILS_ROUTE/{$TYPE_PARAM}/{$ID_PARAM}",
        arguments = listOf(
            navArgument(TYPE_PARAM) { type = NavType.StringType },
            navArgument(ID_PARAM) { type = NavType.IntType }
        )
    ) {
        DetailsRoute(
            modifier = modifier,
            snackbarHostState = snackbarHostState
        )
    }
}

fun NavController.navigateToDetailsScreen(type: ResourceType, id: Int) {
    navigate(route = "$DETAILS_ROUTE/${type.type}/$id")
}
