package com.grandvortex.discogstrackr.feature.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.grandvortex.discogstrackr.domain.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val SEARCH_QUERY = "search_query"
private const val SEARCH_ACTIVE = "search_active"

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val searchQuery = savedStateHandle.getStateFlow(SEARCH_QUERY, "")
    val searchActive = savedStateHandle.getStateFlow(SEARCH_ACTIVE, false)

    fun onSearchActiveChanged(active: Boolean) {
        savedStateHandle[SEARCH_ACTIVE] = active
    }

    fun onSearchTriggered() {
    }

    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
    }
}
