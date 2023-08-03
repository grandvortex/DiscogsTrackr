package com.grandvortex.discogstrackr.presentation.feature.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.grandvortex.discogstrackr.R
import com.grandvortex.discogstrackr.data.ResourceType

@Composable
fun SearchResultItem(
    modifier: Modifier = Modifier,
    type: ResourceType,
    info: String,
    imageUrl: String,
    onClick: () -> Unit
) {
    when (type) {
        ResourceType.MASTER, ResourceType.RELEASE -> {
            val splitArtistTitle = info.split("-", limit = 2)

            val releaseArtist = splitArtistTitle.getOrElse(0) {
                stringResource(id = R.string.unknown_artist)
            }.trim()
            val releaseTitle = splitArtistTitle.getOrElse(1) {
                stringResource(id = R.string.unknown_title)
            }.trim()

            ItemCard(
                modifier = modifier,
                type = type,
                imageUrl = imageUrl,
                title = releaseTitle,
                artist = releaseArtist,
                onClick = onClick
            )
        }

        else -> {
            ItemCard(
                modifier = modifier,
                type = type,
                imageUrl = imageUrl,
                title = info.trim(),
                artist = info.trim(),
                onClick = onClick
            )
        }
    }
}

@Composable
fun ItemText(modifier: Modifier = Modifier, text: String, isBold: Boolean = false) {
    Text(
        modifier = modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp),
        text = text,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
        fontSize = 24.sp
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    type: ResourceType,
    title: String,
    artist: String,
    imageUrl: String,
    onClick: () -> Unit
) {
    OutlinedCard(
        modifier = modifier.padding(bottom = 8.dp).fillMaxWidth().height(240.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = modifier.padding(bottom = 8.dp).fillMaxWidth().weight(1f),
                model = ImageRequest.Builder(context = LocalContext.current).data(imageUrl)
                    .crossfade(true).size(coil.size.Size.ORIGINAL).build(),
                error = painterResource(R.drawable.broken_image),
                placeholder = painterResource(R.drawable.loading_image),
                contentDescription = title,
                contentScale = ContentScale.Crop
            )

            when (type) {
                ResourceType.MASTER, ResourceType.RELEASE -> {
                    ItemText(modifier = modifier, text = title, isBold = true)
                    ItemText(modifier = modifier, text = artist)
                }

                ResourceType.LABEL -> {
                    ItemText(modifier = modifier, text = title, isBold = true)
                }

                else -> {
                    ItemText(modifier = modifier, text = artist, isBold = true)
                }
            }
        }
    }
}

@Composable
@Preview(
    showSystemUi = false,
    showBackground = false
)
fun PreviewSearchResultItem() {
    SearchResultItem(
        type = ResourceType.RELEASE,
        imageUrl = "",
        info = "Matizz / Electrosoul System - Through My Eyes (Future Engineers Re-Set) / Aura",
        onClick = {}
    )
}
