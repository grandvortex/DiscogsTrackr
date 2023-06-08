package com.grandvortex.discogstrackr.feature.search

import com.grandvortex.discogstrackr.domain.model.SearchResult

sealed interface SearchResultState {
    object Loading : SearchResultState
    data class Success(
        val results: List<SearchResult> = emptyList()
    ) : SearchResultState
}
