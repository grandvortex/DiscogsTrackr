package com.grandvortex.discogstrackr.presentation.feature.label

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
import com.grandvortex.discogstrackr.data.model.Label
import com.grandvortex.discogstrackr.data.model.LabelImage
import com.grandvortex.discogstrackr.data.model.ParentLabel
import com.grandvortex.discogstrackr.data.model.Sublabel
import com.grandvortex.discogstrackr.application.theme.DiscogsTrackrTheme
import java.lang.StringBuilder

@Composable
fun LabelRoute(
    modifier: Modifier = Modifier,
    labelViewModel: LabelViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState
) {
    val viewState by labelViewModel.viewStateFlow.collectAsStateWithLifecycle(LabelViewState())

    LabelScreen(
        modifier = modifier,
        snackbarHostState = snackbarHostState,
        viewState = viewState,
        onConsumeError = labelViewModel::onConsumeError
    )
}

@Composable
fun LabelScreen(
    modifier: Modifier,
    snackbarHostState: SnackbarHostState,
    viewState: LabelViewState,
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
    } else if (viewState.labelData == null) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(id = R.string.unknown_label))
        }
    } else {
        LabelConent(modifier = modifier, label = viewState.labelData)
    }
}

@Composable
fun LabelConent(modifier: Modifier, label: Label) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        // Label image
        val image = if (label.images.isNotEmpty()) label.images.first().resourceUrl else ""
        AsyncImage(
            modifier = modifier
                .fillMaxWidth()
                .height(300.dp),
            model = ImageRequest.Builder(context = LocalContext.current).data(image).crossfade(true)
                .size(Size.ORIGINAL).build(),
            error = painterResource(R.drawable.broken_image),
            placeholder = painterResource(id = R.drawable.loading_image),
            contentDescription = label.name,
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            // Label name
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp),
                text = label.name,
                maxLines = 1,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis
            )

            // Label profile
            if (label.profile.isNotEmpty()) {
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.profile),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp),
                    text = label.profile,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // Label sublabels
            if (label.sublabels.isNotEmpty()) {
                val sublabels = StringBuilder()
                label.sublabels.forEachIndexed { index, sublabel ->
                    if (sublabel.name.isNotEmpty()) {
                        sublabels.append(sublabel.name)
                        if (index != label.sublabels.lastIndex) {
                            sublabels.append(", ")
                        }
                    }
                }
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.label_sublabels),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp),
                    text = sublabels.toString(),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // Label parent
            if (label.parentLabel.name.isNotEmpty()) {
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.label_parent),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp),
                    text = label.parentLabel.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // Label contact info
            if (label.contactInfo.isNotEmpty()) {
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.label_contact_info),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp),
                    text = label.contactInfo,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // Label sites
            if (label.urls.isNotEmpty()) {
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.sites),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                label.urls.forEach { site ->
                    if (site.isNotEmpty()) {
                        Text(
                            modifier = modifier.fillMaxWidth(),
                            text = "\u2022  $site",
                            style = MaterialTheme.typography.bodyLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
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
        LabelConent(
            modifier = Modifier, label = Label(
                contactInfo = "Good Looking Records, 84 Queens Road, Watford, Herts WD17 2LA UK Phone: +44(0) 1923 690 700", // ktlint-disable max-line-length
                dataQuality = "Correct",
                id = 124,
                images = listOf(
                    LabelImage(
                        height = 600,
                        resourceUrl = "https://i.discogs.com/ZxshB3P-CLF9rY9FQkADZEgbb_YaPePXk8pfU84yM7E/rs:fit/g:sm/q:90/h:600/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9MLTEyNC0x/MTk2Njk3NzE5Lmpw/ZWc.jpeg",
                        type = "primary",
                        uri = "https://i.discogs.com/ZxshB3P-CLF9rY9FQkADZEgbb_YaPePXk8pfU84yM7E/rs:fit/g:sm/q:90/h:600/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9MLTEyNC0x/MTk2Njk3NzE5Lmpw/ZWc.jpeg",
                        uri150 = "https://i.discogs.com/M4bXyN6PjbvZEuHOYp3Gwt9hzoMB6ICph7cz7RsZA5M/rs:fit/g:sm/q:40/h:150/w:150/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9MLTEyNC0x/MTk2Njk3NzE5Lmpw/ZWc.jpeg",
                        width = 600
                    )
                ),
                name = "Good Looking Records",
                parentLabel = ParentLabel(
                    id = 324968,
                    name = "Good Looking Records Ltd.",
                    resourceUrl = "https://api.discogs.com/labels/324968"
                ),
                profile = "Jungle/Drum'n'Bass label founded in the early 90s by [a=LTJ Bukem].",
                releasesUrl = "",
                resourceUrl = "",
                sublabels = listOf(
                    Sublabel(
                        id = 0,
                        name = "Ascendant Grooves",
                        resourceUrl = "https://api.discogs.com/labels/980"
                    ), Sublabel(
                        id = 0,
                        name = "Blue Vinyl",
                        resourceUrl = "https://api.discogs.com/labels/2234"
                    ), Sublabel(
                        id = 0,
                        name = "Cookin' Records",
                        resourceUrl = "https://api.discogs.com/labels/979"
                    )
                ),
                uri = "",
                urls = listOf(
                    "https://www.facebook.com/goodlookingorganisation",
                    "https://en.wikipedia.org/wiki/Good_Looking_Records"
                )
            )
        )
    }
}
