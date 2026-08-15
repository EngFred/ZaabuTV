package com.engineerfred.zaabutv.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engineerfred.zaabutv.domain.model.User
import com.engineerfred.zaabutv.domain.usecase.GetCurrentUserUseCase
import com.engineerfred.zaabutv.domain.usecase.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProfileUiState(
    val user: User? = null,
    val isLoading: Boolean = true,
    val isLoggedOut: Boolean = false
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getCurrentUserUseCase().collect { currentUser ->
                _uiState.update { it.copy(user = currentUser, isLoading = false) }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase().collect {
                _uiState.update { it.copy(isLoggedOut = true) }
            }
        }
    }
}
