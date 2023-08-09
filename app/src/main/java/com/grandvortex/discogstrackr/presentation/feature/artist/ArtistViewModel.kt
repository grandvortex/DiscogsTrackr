package com.grandvortex.discogstrackr.presentation.feature.artist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grandvortex.discogstrackr.data.model.Artist
import com.grandvortex.discogstrackr.data.remote.RemoteResult
import com.grandvortex.discogstrackr.domain.ResourceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ArtistViewModel @Inject constructor(
    resourceUseCase: ResourceUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Navigation arguments from SearchScreen
    private val id = savedStateHandle.get<Int>(ID_PARAM) ?: -1

    private val _viewStateFlow = MutableStateFlow(ArtistViewState())
    val viewStateFlow = _viewStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            _viewStateFlow.update { state -> state.copy(isLoading = true) }
            updateState(resourceUseCase.getArtist(id))
        }
    }

    private fun updateState(result: RemoteResult<Artist>) {
        when (result) {
            is RemoteResult.Success -> {
                _viewStateFlow.update { state ->
                    state.copy(
                        isLoading = false,
                        artistData = result.data
                    )
                }
            }

            is RemoteResult.Error -> {
                _viewStateFlow.update { state ->
                    state.copy(
                        isLoading = false,
                        error = result.e.message ?: ""
                    )
                }
            }
        }
    }

    fun onConsumeError() {
        _viewStateFlow.update { state ->
            state.copy(error = "")
        }
    }
}