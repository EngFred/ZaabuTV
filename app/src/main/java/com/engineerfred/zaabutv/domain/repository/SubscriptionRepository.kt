package com.engineerfred.zaabutv.domain.repository

import com.engineerfred.zaabutv.domain.model.PaymentMethod
import com.engineerfred.zaabutv.domain.model.SubscriptionPlan
import kotlinx.coroutines.flow.Flow

interface SubscriptionRepository {
    fun getPlans(): Flow<List<SubscriptionPlan>>
    fun subscribe(planId: String, phoneNumber: String, provider: PaymentMethod): Flow<Result<Boolean>>
}
