package com.grandvortex.discogstrackr.presentation.feature.details

import com.grandvortex.discogstrackr.data.ResourceType
import com.grandvortex.discogstrackr.data.model.Artist

data class DetailsViewState(
    val isLoading: Boolean = false,
    val artistData: Artist? = null,
    val error: String = "",
    val resourceType: ResourceType = ResourceType.UNKNOWN
)
