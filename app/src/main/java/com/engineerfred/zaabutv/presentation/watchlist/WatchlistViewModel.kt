package com.engineerfred.zaabutv.presentation.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engineerfred.zaabutv.domain.model.Movie
import com.engineerfred.zaabutv.domain.usecase.GetWatchlistUseCase
import com.engineerfred.zaabutv.domain.usecase.ToggleWatchlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class WatchlistUiState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = true
)

@HiltViewModel
class WatchlistViewModel @Inject constructor(
    private val getWatchlistUseCase: GetWatchlistUseCase,
    private val toggleWatchlistUseCase: ToggleWatchlistUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(WatchlistUiState())
    val uiState: StateFlow<WatchlistUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getWatchlistUseCase()
                .catch { /* ignore */ }
                .collect { list ->
                    _uiState.update { it.copy(movies = list, isLoading = false) }
                }
        }
    }

    fun removeFromWatchlist(movieId: String) {
        viewModelScope.launch {
            toggleWatchlistUseCase(movieId).collect { }
        }
    }
}
