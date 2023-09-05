package com.grandvortex.discogstrackr.presentation.feature.release

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grandvortex.discogstrackr.data.Result
import com.grandvortex.discogstrackr.domain.ReleaseUseCase
import com.grandvortex.discogstrackr.domain.model.Release
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReleaseViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle, releaseUseCase: ReleaseUseCase
) : ViewModel() {

    // Navigation arguments from ReleaseScreen
    private val id = savedStateHandle.get<Int>(RELEASE_ID_PARAM) ?: -1 // Release id

    private val _viewStateFlow = MutableStateFlow(ReleaseViewState())
    val viewStateFlow = _viewStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            _viewStateFlow.update { state -> state.copy(isLoading = true) }
            updateState(releaseUseCase(id))
        }
    }

    private fun updateState(result: Result<Release>) {
        when (result) {
            is Result.Success -> {
                _viewStateFlow.update { state ->
                    state.copy(
                        isLoading = false, releaseData = result.data
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