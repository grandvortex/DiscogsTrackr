package com.grandvortex.discogstrackr.presentation.feature.release

import com.grandvortex.discogstrackr.data.model.Release

data class ReleaseViewState(
    val isLoading: Boolean = false,
    val error: String = "",
    val releaseData: Release? = null
)
