package com.engineerfred.zaabutv.data.repository

import com.engineerfred.zaabutv.data.mockdata.MockPlans
import com.engineerfred.zaabutv.domain.model.PaymentMethod
import com.engineerfred.zaabutv.domain.model.SubscriptionPlan
import com.engineerfred.zaabutv.domain.repository.SubscriptionRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SubscriptionRepositoryImpl @Inject constructor() : SubscriptionRepository {

    override fun getPlans(): Flow<List<SubscriptionPlan>> = flow {
        delay(200)
        emit(MockPlans.plans)
    }

    override fun subscribe(
        planId: String,
        phoneNumber: String,
        provider: PaymentMethod
    ): Flow<Result<Boolean>> = flow {
        // Simulate mobile money USSD prompt / network delay
        delay(1200)
        if (phoneNumber.length >= 9) {
            emit(Result.success(true))
        } else {
            emit(Result.failure(Exception("Invalid mobile money phone number.")))
        }
    }
}
