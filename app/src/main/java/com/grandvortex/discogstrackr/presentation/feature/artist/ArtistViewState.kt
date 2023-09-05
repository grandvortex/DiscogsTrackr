package com.grandvortex.discogstrackr.presentation.feature.artist

import com.grandvortex.discogstrackr.domain.model.Artist

data class ArtistViewState(
    val isLoading: Boolean = false,
    val artistData: Artist? = null,
    val error: String = ""
)
