package com.engineerfred.zaabutv.presentation.moviedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engineerfred.zaabutv.data.datastore.UserPreferencesRepository
import com.engineerfred.zaabutv.domain.model.Actor
import com.engineerfred.zaabutv.domain.model.Movie
import com.engineerfred.zaabutv.domain.model.Vj
import com.engineerfred.zaabutv.domain.usecase.GetMovieDetailUseCase
import com.engineerfred.zaabutv.domain.usecase.ToggleWatchlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MovieDetailUiState(
    val movie: Movie? = null,
    val vj: Vj? = null,
    val cast: List<Actor> = emptyList(),
    val similarMovies: List<Movie> = emptyList(),
    val isInWatchlist: Boolean = false,
    val isSubscribed: Boolean = false,
    val isLoading: Boolean = true,
    val error: String? = null
)

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val toggleWatchlistUseCase: ToggleWatchlistUseCase,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val movieId: String = savedStateHandle["movieId"] ?: ""

    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState: StateFlow<MovieDetailUiState> = _uiState.asStateFlow()

    init {
        observeSubscription()
        loadMovieDetail()
    }

    private fun observeSubscription() {
        viewModelScope.launch {
            userPreferencesRepository.isSubscribed.collect { subscribed ->
                _uiState.update { it.copy(isSubscribed = subscribed) }
            }
        }
    }

    private fun loadMovieDetail() {
        viewModelScope.launch {
            getMovieDetailUseCase(movieId)
                .catch { e ->
                    _uiState.update { it.copy(isLoading = false, error = e.message) }
                }
                .collect { data ->
                    if (data != null) {
                        _uiState.update {
                            it.copy(
                                movie = data.movie,
                                vj = data.vj,
                                cast = data.cast,
                                similarMovies = data.similarMovies,
                                isInWatchlist = data.isInWatchlist,
                                isLoading = false
                            )
                        }
                    } else {
                        _uiState.update { it.copy(isLoading = false, error = "Movie not found.") }
                    }
                }
        }
    }

    fun toggleWatchlist() {
        viewModelScope.launch {
            toggleWatchlistUseCase(movieId).collect { isNowInWatchlist ->
                _uiState.update { it.copy(isInWatchlist = isNowInWatchlist) }
            }
        }
    }
}
