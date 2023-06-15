package com.grandvortex.discogstrackr.feature.search

import com.grandvortex.discogstrackr.data.model.SearchResult

sealed class SearchState {
    object Loading : SearchState()
    data class Success(
        val results: List<SearchResult> = emptyList()
    ) : SearchState()
}
