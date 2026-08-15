package com.engineerfred.zaabutv.presentation.subscription

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engineerfred.zaabutv.domain.model.PaymentMethod
import com.engineerfred.zaabutv.domain.model.SubscriptionPlan
import com.engineerfred.zaabutv.domain.usecase.GetSubscriptionPlansUseCase
import com.engineerfred.zaabutv.domain.usecase.ProcessCheckoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

// ─── Subscription ViewModel ──────────────────────────────────────────

data class SubscriptionUiState(
    val plans: List<SubscriptionPlan> = emptyList(),
    val isLoading: Boolean = true
)

@HiltViewModel
class SubscriptionViewModel @Inject constructor(
    private val getSubscriptionPlansUseCase: GetSubscriptionPlansUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SubscriptionUiState())
    val uiState: StateFlow<SubscriptionUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getSubscriptionPlansUseCase()
                .catch { /* ignore */ }
                .collect { planList ->
                    _uiState.update { it.copy(plans = planList, isLoading = false) }
                }
        }
    }
}

// ─── Checkout ViewModel ──────────────────────────────────────────────

data class CheckoutUiState(
    val plan: SubscriptionPlan? = null,
    val phoneNumber: String = "0771234567",
    val provider: PaymentMethod = PaymentMethod.MTN_MOBILE_MONEY,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getSubscriptionPlansUseCase: GetSubscriptionPlansUseCase,
    private val processCheckoutUseCase: ProcessCheckoutUseCase
) : ViewModel() {

    private val planId: String = savedStateHandle["planId"] ?: ""

    private val _uiState = MutableStateFlow(CheckoutUiState())
    val uiState: StateFlow<CheckoutUiState> = _uiState.asStateFlow()

    init {
        loadPlan()
    }

    private fun loadPlan() {
        viewModelScope.launch {
            getSubscriptionPlansUseCase().collect { plans ->
                val selectedPlan = plans.find { it.id == planId }
                _uiState.update { it.copy(plan = selectedPlan) }
            }
        }
    }

    fun onPhoneNumberChanged(number: String) {
        _uiState.update { it.copy(phoneNumber = number, error = null) }
    }

    fun onProviderSelected(provider: PaymentMethod) {
        _uiState.update { it.copy(provider = provider) }
    }

    fun processPayment() {
        val number = _uiState.value.phoneNumber
        if (number.isBlank() || number.length < 10) {
            _uiState.update { it.copy(error = "Please enter a valid Mobile Money number.") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            processCheckoutUseCase(planId, number, _uiState.value.provider).collect { result ->
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
