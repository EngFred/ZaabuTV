package com.engineerfred.zaabutv.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engineerfred.zaabutv.domain.model.Movie
import com.engineerfred.zaabutv.domain.model.Vj
import com.engineerfred.zaabutv.domain.usecase.GetVjsUseCase
import com.engineerfred.zaabutv.domain.usecase.SearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SearchUiState(
    val query: String = "",
    val results: List<Movie> = emptyList(),
    val vjs: List<Vj> = emptyList(),
    val selectedVjId: String? = null,
    val isSearching: Boolean = false
)

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val getVjsUseCase: GetVjsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private val _queryFlow = MutableStateFlow("")

    init {
        loadVjs()
        observeSearch()
    }

    private fun loadVjs() {
        viewModelScope.launch {
            getVjsUseCase().collect { vjList ->
                _uiState.update { it.copy(vjs = vjList) }
            }
        }
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun observeSearch() {
        viewModelScope.launch {
            _queryFlow
                .debounce(400)
                .flatMapLatest { query ->
                    _uiState.update { it.copy(isSearching = query.isNotBlank()) }
                    searchMoviesUseCase(
                        query = query,
                        vjId = _uiState.value.selectedVjId
                    )
                }
                .catch { /* ignore errors in demo */ }
                .collect { movies ->
                    _uiState.update { it.copy(results = movies, isSearching = false) }
                }
        }
    }

    fun onQueryChanged(query: String) {
        _uiState.update { it.copy(query = query) }
        _queryFlow.value = query
    }

    fun onVjFilterSelected(vjId: String?) {
        _uiState.update { it.copy(selectedVjId = vjId) }
        _queryFlow.value = _uiState.value.query // re-trigger search
    }
}
