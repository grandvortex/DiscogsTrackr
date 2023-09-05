package com.grandvortex.discogstrackr.presentation.feature.label

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grandvortex.discogstrackr.data.Result
import com.grandvortex.discogstrackr.domain.LabelUseCase
import com.grandvortex.discogstrackr.domain.model.Label
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

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

    private fun updateState(result: Result<Label>) {
        when (result) {
            is Result.Success -> {
                _viewStateFlow.update { state ->
                    state.copy(
                        isLoading = false, labelData = result.data
                    )
                }
            }

            is Result.Error -> {
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
