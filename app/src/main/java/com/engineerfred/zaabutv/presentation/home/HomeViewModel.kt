package com.engineerfred.zaabutv.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engineerfred.zaabutv.domain.model.Movie
import com.engineerfred.zaabutv.domain.usecase.GetHomeContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val featured: List<Movie> = emptyList(),
    val newUploads: List<Movie> = emptyList(),
    val classics: List<Movie> = emptyList(),
    val ugandanFilms: List<Movie> = emptyList(),
    val nollywoodFilms: List<Movie> = emptyList(),
    val ghanianFilms: List<Movie> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeContentUseCase: GetHomeContentUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadHomeContent()
    }

    private fun loadHomeContent() {
        viewModelScope.launch {
            getHomeContentUseCase()
                .catch { e ->
                    _uiState.update { it.copy(isLoading = false, error = e.message) }
                }
                .collect { content ->
                    _uiState.update {
                        it.copy(
                            featured = content.featured,
                            newUploads = content.newUploads,
                            classics = content.classics,
                            ugandanFilms = content.ugandanFilms,
                            nollywoodFilms = content.nollywoodFilms,
                            ghanianFilms = content.ghanianFilms,
                            isLoading = false
                        )
                    }
                }
        }
    }
}
