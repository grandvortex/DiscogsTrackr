package com.grandvortex.discogstrackr.presentation.feature.label

import com.grandvortex.discogstrackr.domain.model.Label

data class LabelViewState(
    val isLoading: Boolean = false,
    val error: String = "",
    val labelData: Label? = null
)
