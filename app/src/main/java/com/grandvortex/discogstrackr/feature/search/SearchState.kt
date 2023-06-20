package com.grandvortex.discogstrackr.feature.search

import com.grandvortex.discogstrackr.data.model.SearchResults

data class SearchState(
    val isLoading: Boolean = false,
    val isSearchActive: Boolean = false,
    val data: SearchResults? = null,
    val error: String = ""
)
