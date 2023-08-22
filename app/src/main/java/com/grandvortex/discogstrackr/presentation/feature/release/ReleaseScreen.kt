package com.grandvortex.discogstrackr.presentation.feature.release

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.grandvortex.discogstrackr.R
import com.grandvortex.discogstrackr.data.model.Release
import com.grandvortex.discogstrackr.data.model.ReleaseArtist
import com.grandvortex.discogstrackr.data.model.ReleaseCommunity
import com.grandvortex.discogstrackr.data.model.ReleaseContributor
import com.grandvortex.discogstrackr.data.model.ReleaseFormat
import com.grandvortex.discogstrackr.data.model.ReleaseIdentifier
import com.grandvortex.discogstrackr.data.model.ReleaseImage
import com.grandvortex.discogstrackr.data.model.ReleaseRating
import com.grandvortex.discogstrackr.data.model.ReleaseSubinfo
import com.grandvortex.discogstrackr.data.model.ReleaseSubmitter
import com.grandvortex.discogstrackr.data.model.ReleaseTrack
import com.grandvortex.discogstrackr.data.model.ReleaseVideo
import com.grandvortex.discogstrackr.application.theme.DiscogsTrackrTheme

@Composable
fun ReleaseRoute(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    viewModel: ReleaseViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewStateFlow.collectAsStateWithLifecycle(ReleaseViewState())

    ReleaseScreen(
        modifier = modifier,
        snackbarHostState = snackbarHostState,
        viewState = viewState,
        onConsumeError = viewModel::onConsumeError
    )
}

@Composable
fun ReleaseScreen(
    modifier: Modifier,
    snackbarHostState: SnackbarHostState,
    viewState: ReleaseViewState,
    onConsumeError: () -> Unit
) {
    if (viewState.error.isNotEmpty()) {
        LaunchedEffect(key1 = snackbarHostState) {
            snackbarHostState.showSnackbar(viewState.error)
            onConsumeError()
        }
    }

    if (viewState.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (viewState.releaseData == null) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(id = R.string.unknown_release))
        }
    } else {
        ReleaseContent(modifier = modifier, release = viewState.releaseData)
    }
}

@Composable
fun ReleaseContent(modifier: Modifier, release: Release) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        // Release image
        val image = if (release.images.isNotEmpty()) release.images.first().resourceUrl else ""
        AsyncImage(
            modifier = modifier
                .fillMaxWidth()
                .height(300.dp),
            model = ImageRequest.Builder(context = LocalContext.current).data(image).crossfade(true)
                .size(Size.ORIGINAL).build(),
            error = painterResource(R.drawable.broken_image),
            placeholder = painterResource(id = R.drawable.loading_image),
            contentDescription = release.title,
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            // Release name
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp),
                text = release.title,
                maxLines = 1,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "phone",
    group = "devices",
    device = "spec:shape=Normal,width=412,height=915,unit=dp,dpi=480"
)
@Composable
fun LabelContentPreview() {
    DiscogsTrackrTheme {
        ReleaseContent(
            modifier = Modifier, release = Release(
                artists = emptyList<ReleaseArtist>(),
                artistsSort = "",
                blockedFromSale = false,
                community = ReleaseCommunity(
                    contributors = emptyList<ReleaseContributor>(),
                    dataQuality = "",
                    have = 0,
                    rating = ReleaseRating(
                        average = 0.0, count = 0
                    ),
                    status = "",
                    submitter = ReleaseSubmitter(
                        resourceUrl = "", username = ""
                    ),
                    want = 0
                ),
                companies = emptyList<ReleaseSubinfo>(),
                country = "",
                dataQuality = "",
                dateAdded = "",
                dateChanged = "",
                estimatedWeight = 0,
                extraartists = emptyList<ReleaseArtist>(),
                formatQuantity = 0,
                formats = emptyList<ReleaseFormat>(),
                genres = emptyList<String>(),
                id = 0,
                identifiers = emptyList<ReleaseIdentifier>(),
                images = emptyList<ReleaseImage>(),
                labels = emptyList<ReleaseSubinfo>(),
                lowestPrice = 0.0,
                masterId = 0,
                masterUrl = "",
                notes = "",
                numForSale = 0,
                released = "",
                releasedFormatted = "",
                resourceUrl = "",
                series = emptyList<ReleaseSubinfo>(),
                status = "",
                styles = emptyList<String>(),
                thumb = "",
                title = "Producer 05 - Rarities",
                tracklist = emptyList<ReleaseTrack>(),
                uri = "",
                videos = emptyList<ReleaseVideo>(),
                year = 2002
            )
        )
    }
}