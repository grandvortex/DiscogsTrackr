package com.grandvortex.discogstrackr.presentation.feature.label

import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val LABEL_ROUTE = "label"
const val LABEL_ID_PARAM = "label_id" // Label id

fun NavGraphBuilder.labelScreen(snackbarHostState: SnackbarHostState) {
    composable(
        route = "$LABEL_ROUTE/{$LABEL_ID_PARAM}",
        arguments = listOf(navArgument(LABEL_ID_PARAM) { type = NavType.IntType })
    ) {
        LabelRoute(snackbarHostState = snackbarHostState)
    }
}

fun NavController.navigateToLabelScreen(id: Int) {
    navigate(route = "$LABEL_ROUTE/$id")
}
