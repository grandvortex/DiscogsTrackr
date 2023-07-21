package com.grandvortex.discogstrackr.presentation.feature.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grandvortex.discogstrackr.data.Result
import com.grandvortex.discogstrackr.domain.RecentSearchQueryUseCase
import com.grandvortex.discogstrackr.domain.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val SEARCH_QUERY = "search_query"

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val recentSearchUseCase: RecentSearchQueryUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow = MutableStateFlow(SearchViewState())
    private val _recentSearchFlow = recentSearchUseCase.getAllRecentSearchQueries()

    val viewStateFlow = combine(_stateFlow, _recentSearchFlow) { viewState, recentSearches ->
        viewState.copy(recentSearchData = recentSearches)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SearchViewState()
    )

    var queryText by mutableStateOf("")
        private set

    private val s = snapshotFlow { queryText }.debounce(200).filterNot { it.isEmpty() }

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

                recentSearchUseCase.upsertSearchQuery(queryText)

                when (val result = searchUseCase.invoke(queryText)) {
                    is Result.Success -> {
                        _stateFlow.update { state ->
                            state.copy(
                                searchResultData = result.data,
                                isLoading = false
                            )
                        }
                    }

                    is Result.Error -> {
                        _stateFlow.update { state ->
                            state.copy(
                                error = result.e.message ?: "",
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
        queryText = query
    }
}
