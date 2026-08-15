package com.engineerfred.zaabutv.presentation.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engineerfred.zaabutv.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val pass: String = "",
    val confirmPass: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onNameChanged(name: String) {
        _uiState.update { it.copy(name = name, error = null) }
    }

    fun onEmailChanged(email: String) {
        _uiState.update { it.copy(email = email, error = null) }
    }

    fun onPasswordChanged(pass: String) {
        _uiState.update { it.copy(pass = pass, error = null) }
    }

    fun onConfirmPasswordChanged(pass: String) {
        _uiState.update { it.copy(confirmPass = pass, error = null) }
    }

    fun register() {
        val name = _uiState.value.name
        val email = _uiState.value.email
        val pass = _uiState.value.pass
        val confirmPass = _uiState.value.confirmPass

        if (name.isBlank() || email.isBlank() || pass.isBlank()) {
            _uiState.update { it.copy(error = "Please fill in all fields.") }
            return
        }

        if (pass != confirmPass) {
            _uiState.update { it.copy(error = "Passwords do not match.") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            registerUseCase(name, email, pass).collect { result ->
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
