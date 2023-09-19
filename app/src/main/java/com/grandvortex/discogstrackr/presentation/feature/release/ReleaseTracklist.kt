package com.grandvortex.discogstrackr.presentation.feature.release

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grandvortex.discogstrackr.domain.model.Release
import com.grandvortex.discogstrackr.domain.model.ReleaseArtist
import com.grandvortex.discogstrackr.domain.model.ReleaseTrack

@Composable
fun ReleaseTracklist(modifier: Modifier, release: Release) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(count = release.tracklist.size) { index ->
            if (index > 0) {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    color = Color.White,
                    thickness = 0.5f.dp
                )
            }

            val track = release.tracklist[index]
            TrackItem(track = track)
        }
    }
}

@Composable
fun TrackItem(track: ReleaseTrack) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 6.dp)
    ) {
        // Track position
        Text(
            modifier = Modifier
                .fillMaxWidth(0.1f), text = track.position
        )

        // Track artists and title
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp, end = 4.dp)
        ) {
            // Title
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 2.dp),
                fontWeight = FontWeight.Bold,
                text = track.title
            )
            // Artist
            if (track.artists.isNotEmpty()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 2.dp),
                    text = "by:  ${track.artists.joinToString(", ") { it.name }}"
                )
            }

            // Supporting artists
            track.extraartists.forEach { artist ->
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    text = "${artist.role} - ${artist.name}"
                )
            }
        }

        // Track duration
        Text(
            modifier = Modifier
                .fillMaxWidth(0.1f),
            textAlign = TextAlign.Right,
            text = track.duration
        )

    }
}

@Composable
@Preview(
    showSystemUi = false, showBackground = true
)
fun PreviewTrackItem() {
    val artists = listOf(
        ReleaseArtist(
            anv = "",
            id = 0,
            join = "",
            name = "LTJ Bukem",
            resourceUrl = "",
            role = "",
            tracks = ""
        ), ReleaseArtist(
            anv = "",
            id = 0,
            join = "",
            name = "Herbie Hancock",
            resourceUrl = "",
            role = "",
            tracks = ""
        ), ReleaseArtist(
            anv = "",
            id = 0,
            join = "",
            name = " Grandmixer DXT",
            resourceUrl = "",
            role = "",
            tracks = ""
        ), ReleaseArtist(
            anv = "",
            id = 0,
            join = "",
            name = " Chaka Khan",
            resourceUrl = "",
            role = "",
            tracks = ""
        )
    )

    val extraArtists = listOf(
        ReleaseArtist(
            anv = "",
            id = 0,
            join = "",
            name = "Charnee Moffett",
            resourceUrl = "",
            role = "Bass [Acoustic Bass]",
            tracks = ""
        ), ReleaseArtist(
            anv = "",
            id = 0,
            join = "",
            name = "Herbie Hancock",
            resourceUrl = "",
            role = "Keyboards",
            tracks = ""
        ), ReleaseArtist(
            anv = "",
            id = 0,
            join = "",
            name = " Grandmixer DXT",
            resourceUrl = "",
            role = "Turntables",
            tracks = ""
        ), ReleaseArtist(
            anv = "",
            id = 0,
            join = "",
            name = " Chaka Khan",
            resourceUrl = "",
            role = "Vocals",
            tracks = ""
        )
    )

    val track = ReleaseTrack(
        duration = "4:42",
        extraartists = extraArtists,
        artists = artists,
        position = "1",
        title = "The Essence (LTJ Bukem Remix - Radio Edit)",
        type = "CD"
    )

    TrackItem(track = track)
}

