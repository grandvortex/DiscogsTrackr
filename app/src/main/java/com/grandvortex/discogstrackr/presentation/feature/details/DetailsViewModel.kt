package com.grandvortex.discogstrackr.presentation.feature.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grandvortex.discogstrackr.data.ResourceType
import com.grandvortex.discogstrackr.data.remote.RemoteResult
import com.grandvortex.discogstrackr.data.toResourceType
import com.grandvortex.discogstrackr.domain.ResourceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class DetailsViewModel @Inject constructor(
    resourceUseCase: ResourceUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val id = savedStateHandle.get<Int>(ID_PARAM) ?: -1
    private val type = toResourceType(savedStateHandle.get<String>(TYPE_PARAM))

    private val _viewStateFlow = MutableStateFlow(DetailsViewState(resourceType = type))
    val viewStateFlow = _viewStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            _viewStateFlow.update { state -> state.copy(isLoading = true) }

            val result: RemoteResult<Any> = when (type) {
                ResourceType.ARTIST -> {
                    resourceUseCase.getArtist(id)
                }

                ResourceType.LABEL -> {
                    resourceUseCase.getLabel(id)
                }

                ResourceType.MASTER -> {
                    resourceUseCase.getArtist(id)
                }

                ResourceType.RELEASE -> {
                    resourceUseCase.getArtist(id)
                }

                ResourceType.UNKNOWN -> {
                    resourceUseCase.getArtist(id)
                }
            }
        }
    }

    fun updateState(result: RemoteResult<Any>) {
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
