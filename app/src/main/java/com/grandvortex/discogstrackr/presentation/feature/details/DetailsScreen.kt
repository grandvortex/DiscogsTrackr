package com.grandvortex.discogstrackr.presentation.feature.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.grandvortex.discogstrackr.data.ResourceType
import com.grandvortex.discogstrackr.theme.DiscogsTrackrTheme
import com.grandvortex.discogstrackr.utils.DevicePreviews

@Composable
fun DetailsRoute(
    modifier: Modifier,
    detailsViewModel: DetailsViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState
) {
    val viewState by detailsViewModel.viewStateFlow.collectAsStateWithLifecycle(DetailsViewState())

    DetailsScreen(
        modifier = modifier,
        snackbarHostState = snackbarHostState,
        state = viewState,
        onConsumeError = detailsViewModel::onConsumeError
    )
}

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    state: DetailsViewState,
    onConsumeError: () -> Unit
) {
    // Display errors
    if (state.error.isNotEmpty()) {
        LaunchedEffect(key1 = snackbarHostState) {
            snackbarHostState.showSnackbar(state.error)
            onConsumeError()
        }
    }

    if (state.isLoading) {
        Box(
            modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (state.resourceType) {
                ResourceType.ARTIST -> {
                }

                ResourceType.LABEL -> {
                }

                ResourceType.MASTER -> {
                }

                ResourceType.RELEASE -> {
                }

                ResourceType.UNKNOWN -> {
                }
            }
        }
    }
}

@DevicePreviews
@Composable
fun DetailScreenPreview() {
    DiscogsTrackrTheme {
        DetailsScreen(
            snackbarHostState = SnackbarHostState(),
            state = DetailsViewState(),
            onConsumeError = {}
        )
    }
}
