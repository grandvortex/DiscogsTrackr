package com.grandvortex.discogstrackr.presentation.feature.artist

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
import com.grandvortex.discogstrackr.application.theme.DiscogsTrackrTheme
import com.grandvortex.discogstrackr.domain.model.Aliase
import com.grandvortex.discogstrackr.domain.model.Artist
import com.grandvortex.discogstrackr.presentation.utils.onCondition

@Composable
fun ArtistRoute(
    modifier: Modifier = Modifier,
    artistViewModel: ArtistViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState
) {
    val viewState by artistViewModel.viewStateFlow.collectAsStateWithLifecycle(ArtistViewState())

    ArtistScreen(
        modifier = modifier,
        snackbarHostState = snackbarHostState,
        viewState = viewState,
        onConsumeError = artistViewModel::onConsumeError
    )
}

@Composable
fun ArtistScreen(
    modifier: Modifier,
    snackbarHostState: SnackbarHostState,
    viewState: ArtistViewState,
    onConsumeError: () -> Unit
) {
    // Display errors
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
    } else if (viewState.artistData == null) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(id = R.string.unknown_artist))
        }
    } else {
        ArtistContent(modifier = modifier, artist = viewState.artistData)
    }
}

@Composable
fun ArtistContent(
    modifier: Modifier, artist: Artist
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        // Artist image
        val image = if (artist.images.isNotEmpty()) artist.images.first().resourceUrl else ""
        AsyncImage(
            modifier = modifier
                .fillMaxWidth()
                .height(300.dp),
            model = ImageRequest.Builder(context = LocalContext.current).data(image).crossfade(true)
                .size(Size.ORIGINAL).build(),
            error = painterResource(R.drawable.broken_image),
            placeholder = painterResource(id = R.drawable.loading_image),
            contentDescription = artist.name,
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            // Artist name
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp),
                text = artist.name,
                maxLines = 1,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis
            )

            // Artist real name
            if (artist.realname.isNotEmpty()) {
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.artist_real_name),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp),
                    text = artist.realname,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // Artist profile
            if (artist.profile.isNotEmpty()) {
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.profile),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp),
                    text = artist.profile,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // Artist sites
            if (artist.urls.isNotEmpty()) {
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.sites),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                artist.urls.forEachIndexed { index, site ->
                    if (site.isNotEmpty()) {
                        Text(
                            modifier = modifier
                                .fillMaxWidth()
                                .onCondition(condition = index == artist.urls.lastIndex,
                                    onTrue = { padding(bottom = 14.dp) }),
                            text = "\u2022  $site",
                            style = MaterialTheme.typography.bodyLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }

            // Artist members
            if (artist.members.isNotEmpty()) {
                val members = artist.members.joinToString(", ") { it.name }

                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.artist_members),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp),
                    text = members,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // Artist aliases
            if (artist.aliases.isNotEmpty()) {
                val aliases = artist.aliases.joinToString(", ") { it.name }

                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.artist_aliases),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp),
                    text = aliases,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // Artist name variations
            if (artist.namevariations.isNotEmpty()) {
                val variations = artist.namevariations.joinToString(", ")

                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.artist_name_variations),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = variations,
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
fun ArtistContentPreview() {
    DiscogsTrackrTheme {
        ArtistContent(
            modifier = Modifier, artist = Artist(
                aliases = listOf(
                    Aliase(
                        id = 0, name = "Daniel Williamson", resourceUrl = ""
                    ), Aliase(
                        id = 0, name = "The Bookworm", resourceUrl = ""
                    )
                ),
                dataQuality = "",
                id = -1,
                images = emptyList(),
                name = "LTJ Bukem",
                namevariations = listOf(
                    "Bukem", "DJ LTJ Bukem", "L.T.J. Bukem"
                ),
                profile = "British drum'n'bass DJ, producer/remixer.\n" + "Born: 20 September 1967 in London, England, UK.\n" + "His alias LTJ Bukem is from popular Hawaii Five-O cop show catch phrase Book 'Em Danno as Williamson's name is almost like the main character 'Danny Danno Williams'.", // ktlint-disable max-line-length
                realname = "Danny Williamson",
                releasesUrl = "",
                resourceUrl = "",
                uri = "",
                urls = listOf(
                    "Facebook",
                    "Twitter",
                    "Soundcloud",
                    "Mixcloud",
                    "MySpace",
                    "Instagram",
                    "Spotify",
                    "Wikipedia"
                ),
                members = emptyList()
            )
        )
    }
}
