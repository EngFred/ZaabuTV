package com.engineerfred.zaabutv.presentation.vj

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engineerfred.zaabutv.domain.model.Movie
import com.engineerfred.zaabutv.domain.model.Vj
import com.engineerfred.zaabutv.domain.usecase.GetVjProfileUseCase
import com.engineerfred.zaabutv.domain.usecase.GetVjsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

// ─── VJ Directory ViewModel ──────────────────────────────────────────

data class VjDirectoryUiState(
    val vjs: List<Vj> = emptyList(),
    val isLoading: Boolean = true
)

@HiltViewModel
class VjDirectoryViewModel @Inject constructor(
    private val getVjsUseCase: GetVjsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(VjDirectoryUiState())
    val uiState: StateFlow<VjDirectoryUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getVjsUseCase()
                .catch { /* ignore in demo */ }
                .collect { vjList ->
                    _uiState.update { it.copy(vjs = vjList, isLoading = false) }
                }
        }
    }
}

// ─── VJ Profile ViewModel ───────────────────────────────────────────

data class VjProfileUiState(
    val vj: Vj? = null,
    val translatedMovies: List<Movie> = emptyList(),
    val isLoading: Boolean = true
)

@HiltViewModel
class VjProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getVjProfileUseCase: GetVjProfileUseCase
) : ViewModel() {

    private val vjId: String = savedStateHandle["vjId"] ?: ""

    private val _uiState = MutableStateFlow(VjProfileUiState())
    val uiState: StateFlow<VjProfileUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getVjProfileUseCase(vjId)
                .catch { /* ignore in demo */ }
                .collect { data ->
                    if (data != null) {
                        _uiState.update {
                            it.copy(
                                vj = data.vj,
                                translatedMovies = data.translatedMovies,
                                isLoading = false
                            )
                        }
                    } else {
                        _uiState.update { it.copy(isLoading = false) }
                    }
                }
        }
    }
}
