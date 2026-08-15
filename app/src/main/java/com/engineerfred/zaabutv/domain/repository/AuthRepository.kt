package com.engineerfred.zaabutv.domain.repository

import com.engineerfred.zaabutv.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(email: String, pass: String): Flow<Result<User>>
    fun register(name: String, email: String, pass: String): Flow<Result<User>>
    fun getCurrentUser(): Flow<User?>
    fun logout(): Flow<Unit>
    fun forgotPassword(email: String): Flow<Result<Unit>>
}
