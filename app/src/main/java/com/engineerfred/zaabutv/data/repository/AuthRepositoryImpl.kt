package com.engineerfred.zaabutv.data.repository

import com.engineerfred.zaabutv.data.datastore.UserPreferencesRepository
import com.engineerfred.zaabutv.domain.model.User
import com.engineerfred.zaabutv.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) : AuthRepository {

    override fun login(email: String, pass: String): Flow<Result<User>> = flow {
        delay(800)
        if (email.isNotBlank() && pass.length >= 4) {
            val name = email.substringBefore("@").replaceFirstChar { it.uppercase() }
            userPreferencesRepository.saveUserData(name, email)
            val loggedInUser = User(
                id = "user_${System.currentTimeMillis()}",
                name = name,
                email = email,
                isSubscribed = false,
                activePlanName = null
            )
            emit(Result.success(loggedInUser))
        } else {
            emit(Result.failure(Exception("Invalid email or password.")))
        }
    }

    override fun register(name: String, email: String, pass: String): Flow<Result<User>> = flow {
        delay(900)
        if (name.isNotBlank() && email.isNotBlank() && pass.length >= 4) {
            userPreferencesRepository.saveUserData(name, email)
            val newUser = User(
                id = "user_${System.currentTimeMillis()}",
                name = name,
                email = email,
                isSubscribed = false,
                activePlanName = null
            )
            emit(Result.success(newUser))
        } else {
            emit(Result.failure(Exception("Please fill in all required registration fields.")))
        }
    }

    override fun getCurrentUser(): Flow<User?> {
        return combine(
            userPreferencesRepository.userName,
            userPreferencesRepository.userEmail,
            userPreferencesRepository.isSubscribed,
            userPreferencesRepository.activePlanId
        ) { name, email, isSubscribed, planId ->
            if (name == null || email == null) {
                null
            } else {
                User(
                    id = "user_persisted",
                    name = name,
                    email = email,
                    isSubscribed = isSubscribed,
                    activePlanName = when (planId) {
                        "plan_weekly" -> "Weekly Pass"
                        "plan_monthly" -> "Monthly Pass"
                        "plan_quarterly" -> "3-Month Pass"
                        "plan_yearly" -> "1-Year Pass"
                        else -> if (isSubscribed) "VIP Pass" else null
                    }
                )
            }
        }
    }

    override fun logout(): Flow<Unit> = flow {
        delay(300)
        userPreferencesRepository.clearSession()
        emit(Unit)
    }

    override fun forgotPassword(email: String): Flow<Result<Unit>> = flow {
        delay(700)
        if (email.contains("@")) {
            emit(Result.success(Unit))
        } else {
            emit(Result.failure(Exception("Please enter a valid email address.")))
        }
    }
}
