package com.engineerfred.zaabutv.data.repository

import com.engineerfred.zaabutv.data.mockdata.MockPlans
import com.engineerfred.zaabutv.domain.model.User
import com.engineerfred.zaabutv.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor() : AuthRepository {

    private val currentUser = MutableStateFlow<User?>(MockPlans.defaultUser)

    override fun login(email: String, pass: String): Flow<Result<User>> = flow {
        delay(800)
        if (email.isNotBlank() && pass.length >= 4) {
            val loggedInUser = User(
                id = "user_${System.currentTimeMillis()}",
                name = email.substringBefore("@").replaceFirstChar { it.uppercase() },
                email = email,
                isSubscribed = true,
                activePlanName = "Monthly Pass"
            )
            currentUser.value = loggedInUser
            emit(Result.success(loggedInUser))
        } else {
            emit(Result.failure(Exception("Invalid email or password.")))
        }
    }

    override fun register(name: String, email: String, pass: String): Flow<Result<User>> = flow {
        delay(900)
        if (name.isNotBlank() && email.isNotBlank() && pass.length >= 4) {
            val newUser = User(
                id = "user_${System.currentTimeMillis()}",
                name = name,
                email = email,
                isSubscribed = false,
                activePlanName = null
            )
            currentUser.value = newUser
            emit(Result.success(newUser))
        } else {
            emit(Result.failure(Exception("Please fill in all required registration fields.")))
        }
    }

    override fun getCurrentUser(): Flow<User?> = currentUser

    override fun logout(): Flow<Unit> = flow {
        delay(300)
        currentUser.value = null
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
