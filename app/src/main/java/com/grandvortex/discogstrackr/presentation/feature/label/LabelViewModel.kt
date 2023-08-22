package com.grandvortex.discogstrackr.presentation.feature.label

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grandvortex.discogstrackr.data.model.Label
import com.grandvortex.discogstrackr.data.remote.RemoteResult
import com.grandvortex.discogstrackr.domain.LabelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class LabelViewModel @Inject constructor(
    labelUseCase: LabelUseCase, savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Navigation arguments from SearchScreen
    private val id = savedStateHandle.get<Int>(LABEL_ID_PARAM) ?: -1 // Label id

    private val _viewStateFlow = MutableStateFlow(LabelViewState())
    val viewStateFlow = _viewStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            _viewStateFlow.update { state -> state.copy(isLoading = true) }
            updateState(labelUseCase(id))
        }
    }

    private fun updateState(result: RemoteResult<Label>) {
        when (result) {
            is RemoteResult.Success -> {
                _viewStateFlow.update { state ->
                    state.copy(
                        isLoading = false, labelData = result.data
                    )
                }
            }

            is RemoteResult.Error -> {
                _viewStateFlow.update { state ->
                    state.copy(
                        isLoading = false, error = result.e.message ?: ""
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
