package com.engineerfred.zaabutv.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engineerfred.zaabutv.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginUiState(
    val email: String = "",
    val pass: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChanged(email: String) {
        _uiState.update { it.copy(email = email, error = null) }
    }

    fun onPasswordChanged(pass: String) {
        _uiState.update { it.copy(pass = pass, error = null) }
    }

    fun login() {
        val email = _uiState.value.email
        val pass = _uiState.value.pass

        if (email.isBlank() || pass.isBlank()) {
            _uiState.update { it.copy(error = "Please enter both email and password.") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            loginUseCase(email, pass).collect { result ->
                result.fold(
                    onSuccess = {
                        _uiState.update { state -> state.copy(isLoading = false, isSuccess = true) }
                    },
                    onFailure = { err ->
                        _uiState.update { state -> state.copy(isLoading = false, error = err.message) }
                    }
                )
            }
        }
    }
}
