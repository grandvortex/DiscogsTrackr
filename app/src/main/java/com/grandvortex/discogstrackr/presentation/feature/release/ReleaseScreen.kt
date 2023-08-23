package com.grandvortex.discogstrackr.presentation.feature.release

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.font.FontStyle
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
import com.grandvortex.discogstrackr.application.theme.DiscogsTrackrTheme
import com.grandvortex.discogstrackr.data.model.Release
import com.grandvortex.discogstrackr.data.model.ReleaseArtist
import com.grandvortex.discogstrackr.data.model.ReleaseCommunity
import com.grandvortex.discogstrackr.data.model.ReleaseFormat
import com.grandvortex.discogstrackr.data.model.ReleaseRating
import com.grandvortex.discogstrackr.data.model.ReleaseSubinfo
import com.grandvortex.discogstrackr.data.model.ReleaseSubmitter
import com.grandvortex.discogstrackr.presentation.utils.onCondition

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
                modifier = modifier.fillMaxWidth(),
                text = release.title,
                maxLines = 1,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis
            )

            // Release artists
            if (release.artists.isNotEmpty()) {
                val artist = release.artists.joinToString(", ") { it.name }

                Row(
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(
                        modifier = modifier
                            .padding(end = 4.dp)
                            .alignByBaseline(),
                        text = stringResource(id = R.string.artist),
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Light,
                        fontStyle = FontStyle.Italic
                    )
                    Text(
                        modifier = modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .alignByBaseline(),
                        text = artist,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            // Release date
            if (release.releasedFormatted.isNotEmpty()) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp)
                ) {
                    Text(
                        modifier = modifier
                            .padding(end = 4.dp)
                            .alignByBaseline(),
                        text = stringResource(id = R.string.released),
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Light,
                        fontStyle = FontStyle.Italic
                    )
                    Text(
                        modifier = modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .alignByBaseline(),
                        text = release.releasedFormatted,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            // Release labels
            if (release.labels.isNotEmpty()) {
                val labels = release.labels.joinToString(", ") { it.name }

                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.label),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp),
                    text = labels,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // Release series
            if (release.series.isNotEmpty()) {
                val series = release.series.joinToString(", ") { it.name }

                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.series),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp),
                    text = series,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // Release formats
            if (release.formats.isNotEmpty()) {
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.format),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                release.formats.forEachIndexed { index, format ->
                    if (format.name.isNotEmpty()) {
                        val description = format.descriptions.joinToString(", ")

                        Text(
                            modifier = modifier
                                .fillMaxWidth()
                                .onCondition(condition = index == release.formats.lastIndex,
                                    onTrue = { padding(bottom = 14.dp) }),
                            text = "${format.name}: $description",
                            style = MaterialTheme.typography.bodyLarge,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }

            // Release country
            if (release.country.isNotEmpty()) {
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.country),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp),
                    text = release.country,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // Release genre
            if (release.genres.isNotEmpty()) {
                val genres = release.genres.joinToString(", ")

                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.genre),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp),
                    text = genres,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // Release styles
            if (release.styles.isNotEmpty()) {
                val styles = release.styles.joinToString(", ")

                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.style),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp),
                    text = styles,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
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
                artists = listOf(
                    ReleaseArtist(
                        anv = "",
                        id = -1,
                        join = "",
                        name = "LTJ Bukem",
                        resourceUrl = "",
                        role = "",
                        tracks = ""
                    ), ReleaseArtist(
                        anv = "",
                        id = -1,
                        join = "",
                        name = "DJ Sy",
                        resourceUrl = "",
                        role = "",
                        tracks = ""
                    ), ReleaseArtist(
                        anv = "",
                        id = -1,
                        join = "",
                        name = "Calibre",
                        resourceUrl = "",
                        role = "",
                        tracks = ""
                    )
                ),
                artistsSort = "",
                blockedFromSale = false,
                community = ReleaseCommunity(
                    contributors = emptyList(),
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
                companies = emptyList(),
                country = "UK",
                dataQuality = "",
                dateAdded = "",
                dateChanged = "",
                estimatedWeight = 0,
                extraartists = emptyList(),
                formatQuantity = 0,
                formats = listOf(
                    ReleaseFormat(
                        name = "CD",
                        qty = "",
                        descriptions = listOf("Compilation")
                    ),
                    ReleaseFormat(
                        name = "Tape",
                        qty = "",
                        descriptions = listOf("Single Track")
                    ),
                    ReleaseFormat(
                        name = "Vinyl",
                        qty = "",
                        descriptions = listOf("Single")
                    )
                ),
                genres = listOf("Drum and Bass, Electronic, Intelligent DnB"),
                id = 0,
                identifiers = emptyList(),
                images = emptyList(),
                labels = listOf(
                    ReleaseSubinfo(
                        name = "Good Looking Records",
                        catno = "",
                        entityType = "",
                        entityTypeName = "",
                        id = -1,
                        resourceUrl = ""
                    ), ReleaseSubinfo(
                        name = "Looking Good Records",
                        catno = "",
                        entityType = "",
                        entityTypeName = "",
                        id = -1,
                        resourceUrl = ""
                    ), ReleaseSubinfo(
                        name = "Soul:r",
                        catno = "",
                        entityType = "",
                        entityTypeName = "",
                        id = -1,
                        resourceUrl = ""
                    ), ReleaseSubinfo(
                        name = "Metalheads",
                        catno = "",
                        entityType = "",
                        entityTypeName = "",
                        id = -1,
                        resourceUrl = ""
                    )
                ),
                lowestPrice = 0.0,
                masterId = 0,
                masterUrl = "",
                notes = "",
                numForSale = 0,
                released = "",
                releasedFormatted = "06 May 2002",
                resourceUrl = "",
                series = listOf(
                    ReleaseSubinfo(
                        name = "Producer",
                        catno = "",
                        entityType = "",
                        entityTypeName = "",
                        id = -1,
                        resourceUrl = ""
                    ), ReleaseSubinfo(
                        name = "Earth",
                        catno = "",
                        entityType = "",
                        entityTypeName = "",
                        id = -1,
                        resourceUrl = ""
                    ), ReleaseSubinfo(
                        name = "Logical Progressions",
                        catno = "",
                        entityType = "",
                        entityTypeName = "",
                        id = -1,
                        resourceUrl = ""
                    ), ReleaseSubinfo(
                        name = "Progression Sessions",
                        catno = "",
                        entityType = "",
                        entityTypeName = "",
                        id = -1,
                        resourceUrl = ""
                    )
                ),
                status = "",
                styles = listOf("DnB, Electronic"),
                thumb = "",
                title = "Producer 05 - Rarities",
                tracklist = emptyList(),
                uri = "",
                videos = emptyList(),
                year = 2002
            )
        )
    }
}