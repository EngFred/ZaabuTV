package com.engineerfred.zaabutv.data.mockdata

import com.engineerfred.zaabutv.domain.model.SubscriptionPlan
import com.engineerfred.zaabutv.domain.model.User

object MockPlans {
    val plans = listOf(
        SubscriptionPlan(
            id = "plan_weekly",
            name = "Weekly Access",
            priceUgx = 8000,
            periodLabel = "per week",
            features = listOf(
                "7 days unlimited streaming",
                "HD 720p Video Quality",
                "Access all VJ-translated movies",
                "Stream on 1 mobile device",
                "MTN / Airtel Mobile Money"
            ),
            isPopular = false
        ),
        SubscriptionPlan(
            id = "plan_monthly",
            name = "Monthly Pass",
            priceUgx = 35000,
            periodLabel = "per month",
            features = listOf(
                "30 days unlimited streaming",
                "Full HD 1080p Quality",
                "Access all VJ-translated & Ugandan films",
                "Stream on 2 devices simultaneously",
                "Download movies for offline viewing",
                "Ad-free cinematic experience"
            ),
            isPopular = true
        ),
        SubscriptionPlan(
            id = "plan_quarterly",
            name = "3-Month Pass",
            priceUgx = 90000,
            periodLabel = "per 3 months",
            features = listOf(
                "90 days unlimited streaming",
                "Save UGX 15,000 vs monthly",
                "Full HD 1080p Quality",
                "Stream on 3 devices simultaneously",
                "Unlimited offline downloads"
            ),
            isPopular = false
        ),
        SubscriptionPlan(
            id = "plan_yearly",
            name = "1-Year Pass",
            priceUgx = 320000,
            periodLabel = "per year",
            features = listOf(
                "365 days unlimited VIP access",
                "Ultra HD 4K Quality",
                "Early access to new VJ releases",
                "Stream on 4 devices simultaneously",
                "Unlimited offline downloads",
                "Save over UGX 100,000 annually"
            ),
            isPopular = false
        )
    )

    val defaultUser = User(
        id = "user_demo",
        name = "New Member",
        email = "member@zaabutv.ug",
        avatarUrl = null,
        isSubscribed = false,
        activePlanName = null
    )
}
