package com.grandvortex.discogstrackr.presentation.feature.search

import com.grandvortex.discogstrackr.data.model.RecentSearch
import com.grandvortex.discogstrackr.data.model.SearchResults

data class SearchState(
    val isLoading: Boolean = false,
    val isSearchActive: Boolean = false,
    val searchResultData: SearchResults? = null,
    val recentSearchData: List<RecentSearch>? = null,
    val error: String = ""
)
