package com.engineerfred.zaabutv.presentation.auth.forgotpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engineerfred.zaabutv.domain.usecase.ForgotPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ForgotPasswordUiState(
    val email: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSent: Boolean = false
)

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val forgotPasswordUseCase: ForgotPasswordUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ForgotPasswordUiState())
    val uiState: StateFlow<ForgotPasswordUiState> = _uiState.asStateFlow()

    fun onEmailChanged(email: String) {
        _uiState.update { it.copy(email = email, error = null) }
    }

    fun sendResetLink() {
        val email = _uiState.value.email
        if (email.isBlank()) {
            _uiState.update { it.copy(error = "Please enter your email address.") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            forgotPasswordUseCase(email).collect { result ->
                result.fold(
                    onSuccess = {
                        _uiState.update { state -> state.copy(isLoading = false, isSent = true) }
                    },
                    onFailure = { err ->
                        _uiState.update { state -> state.copy(isLoading = false, error = err.message) }
                    }
                )
            }
        }
    }
}
