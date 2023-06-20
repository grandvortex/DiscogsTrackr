package com.grandvortex.discogstrackr.feature.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grandvortex.discogstrackr.data.remote.repository.NetworkResult
import com.grandvortex.discogstrackr.domain.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val SEARCH_QUERY = "search_query"

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _viewState: MutableStateFlow<SearchState> = MutableStateFlow(SearchState())
    val viewState = _viewState.asStateFlow()

    var queryText: MutableState<String> = mutableStateOf("")
        private set

    init {
        queryText.value = savedStateHandle[SEARCH_QUERY] ?: ""
        onSearchTriggered()
    }

    fun onSearchActiveChanged(active: Boolean) {
        _viewState.update { state -> state.copy(isSearchActive = active) }
    }

    fun onSearchTriggered() {
        if (queryText.value.isNotEmpty()) {
            _viewState.update { state -> state.copy(isLoading = true) }

            viewModelScope.launch {
                when (val result = searchUseCase.invoke(queryText.value)) {
                    is NetworkResult.Success -> {
                        _viewState.update { state -> state.copy(data = result.data) }
                    }

                    is NetworkResult.HttpError -> {
                        _viewState.update { state -> state.copy(error = result.message) }
                    }

                    is NetworkResult.NetworkError -> {
                        _viewState.update { state -> state.copy(error = result.message) }
                    }
                }
            }

            _viewState.update { state -> state.copy(isLoading = false) }
        }
    }

    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
        queryText.value = query
    }
}
