package com.grandvortex.discogstrackr.presentation.feature.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grandvortex.discogstrackr.data.ResourceType
import com.grandvortex.discogstrackr.data.remote.RemoteResult
import com.grandvortex.discogstrackr.domain.RecentSearchQueryUseCase
import com.grandvortex.discogstrackr.domain.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val SEARCH_QUERY = "search_query"

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val recentSearchUseCase: RecentSearchQueryUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var queryText by mutableStateOf("")
        private set

    private val _stateFlow = MutableStateFlow(SearchViewState())
    private val _recentSearchFlow = recentSearchUseCase.getAllRecentSearchQueries()

    val viewStateFlow = combine(_stateFlow, _recentSearchFlow) { viewState, recentSearches ->
        viewState.copy(recentSearchData = recentSearches)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SearchViewState()
    )

    init {
        queryText = savedStateHandle[SEARCH_QUERY] ?: ""
        onSearchTriggered()
    }

    fun onRecentSearchItemDeleted(query: String) {
        viewModelScope.launch { recentSearchUseCase.deleteSearchQuery(query) }
    }

    fun onRecentSearchCleared() {
        viewModelScope.launch { recentSearchUseCase.clearRecentSearchQueries() }
    }

    fun onSearchActiveChanged(active: Boolean) {
        _stateFlow.update { state -> state.copy(isSearchActive = active) }
    }

    fun onSearchTriggered() {
        if (queryText.isNotEmpty()) {
            viewModelScope.launch {
                _stateFlow.update { state -> state.copy(isLoading = true) }

                when (val result = searchUseCase.invoke(queryText)) {
                    is RemoteResult.Success -> {
                        _stateFlow.update { state ->
                            val list =
                                result.data.results.filterNot { it.type == ResourceType.MASTER }
                            state.copy(
                                searchResultData = result.data.copy(results = list),
                                isLoading = false
                            )
                        }
                    }

                    is RemoteResult.Error -> {
                        _stateFlow.update { state ->
                            state.copy(
                                error = result.e.message ?: "", isLoading = false
                            )
                        }
                    }
                }
                recentSearchUseCase.upsertSearchQuery(queryText)
            }
        }
    }

    fun onConsumeError() {
        _stateFlow.update { state ->
            state.copy(
                error = ""
            )
        }
    }

    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
        queryText = query
    }

    fun onRecentSearchQueryClick(query: String) {
        onSearchQueryChanged(query)
        onSearchActiveChanged(false)
        onSearchTriggered()
    }
}
