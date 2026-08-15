package com.engineerfred.zaabutv.data.mockdata

import com.engineerfred.zaabutv.domain.model.SubscriptionPlan
import com.engineerfred.zaabutv.domain.model.User

object MockPlans {
    val plans = listOf(
        SubscriptionPlan(
            id = "plan_weekly",
            name = "Weekly Access",
            priceUgx = 5000,
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
            priceUgx = 15000,
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
            id = "plan_yearly",
            name = "VIP Annual Pass",
            priceUgx = 150000,
            periodLabel = "per year",
            features = listOf(
                "365 days unlimited VIP access",
                "Ultra HD 4K Quality",
                "Early access to new VJ uploads",
                "Stream on 4 devices simultaneously",
                "Unlimited offline downloads",
                "Exclusive VJ meet & greet events"
            ),
            isPopular = false
        )
    )

    val defaultUser = User(
        id = "user_demo",
        name = "Engineer Fred",
        email = "fred@zaabutv.com",
        avatarUrl = "https://images.unsplash.com/photo-1534528741775-53994a69daeb?auto=format&fit=crop&q=80&w=300",
        isSubscribed = true,
        activePlanName = "Monthly Pass"
    )
}
