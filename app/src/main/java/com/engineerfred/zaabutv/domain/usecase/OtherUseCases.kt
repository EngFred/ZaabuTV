package com.engineerfred.zaabutv.domain.usecase

import com.engineerfred.zaabutv.domain.model.Movie
import com.engineerfred.zaabutv.domain.model.PaymentMethod
import com.engineerfred.zaabutv.domain.model.SubscriptionPlan
import com.engineerfred.zaabutv.domain.model.User
import com.engineerfred.zaabutv.domain.repository.AuthRepository
import com.engineerfred.zaabutv.domain.repository.SubscriptionRepository
import com.engineerfred.zaabutv.domain.repository.WatchlistRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWatchlistUseCase @Inject constructor(
    private val watchlistRepository: WatchlistRepository
) {
    operator fun invoke(): Flow<List<Movie>> = watchlistRepository.getWatchlist()
}

class ToggleWatchlistUseCase @Inject constructor(
    private val watchlistRepository: WatchlistRepository
) {
    operator fun invoke(movieId: String): Flow<Boolean> = watchlistRepository.toggleWatchlist(movieId)
}

class GetSubscriptionPlansUseCase @Inject constructor(
    private val subscriptionRepository: SubscriptionRepository
) {
    operator fun invoke(): Flow<List<SubscriptionPlan>> = subscriptionRepository.getPlans()
}

class ProcessCheckoutUseCase @Inject constructor(
    private val subscriptionRepository: SubscriptionRepository
) {
    operator fun invoke(
        planId: String,
        phoneNumber: String,
        provider: PaymentMethod
    ): Flow<Result<Boolean>> {
        return subscriptionRepository.subscribe(planId, phoneNumber, provider)
    }
}

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String, pass: String): Flow<Result<User>> =
        authRepository.login(email, pass)
}

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(name: String, email: String, pass: String): Flow<Result<User>> =
        authRepository.register(name, email, pass)
}

class ForgotPasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String): Flow<Result<Unit>> =
        authRepository.forgotPassword(email)
}

class GetCurrentUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Flow<User?> = authRepository.getCurrentUser()
}

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Flow<Unit> = authRepository.logout()
}
