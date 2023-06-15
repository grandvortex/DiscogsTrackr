package com.grandvortex.discogstrackr.feature.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grandvortex.discogstrackr.domain.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

private const val SEARCH_QUERY = "search_query"
private const val SEARCH_ACTIVE = "search_active"

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var queryState = mutableStateOf("")
        private set

    val searchActive = savedStateHandle.getStateFlow(SEARCH_ACTIVE, false)

    init {
        queryState.value = savedStateHandle[SEARCH_QUERY] ?: ""
    }

    fun onSearchActiveChanged(active: Boolean) {
        savedStateHandle[SEARCH_ACTIVE] = active
    }

    fun onSearchTriggered() {
        viewModelScope.launch {
            searchUseCase.invoke(queryState.value)
        }
    }

    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
        queryState.value = query
    }
}
