package com.grandvortex.discogstrackr.presentation.feature.details

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.grandvortex.discogstrackr.theme.DiscogsTrackrTheme
import com.grandvortex.discogstrackr.utils.DevicePreviews

@Composable
fun DetailsRoute(
    modifier: Modifier,
    detailViewModel: DetailViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState,
    type: String,
    id: Int
) {
    DetailsScreen(type, id)
}

@Composable
fun DetailsScreen(type: String, id: Int) {
    Column() {}
}

@DevicePreviews
@Composable
fun DetailScreenPreview() {
    DiscogsTrackrTheme {
        DetailsScreen(type = "artist", id = 1)
    }
}
