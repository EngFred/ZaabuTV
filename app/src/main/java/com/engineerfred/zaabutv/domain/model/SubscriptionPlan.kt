package com.engineerfred.zaabutv.domain.model

data class SubscriptionPlan(
    val id: String,
    val name: String,
    val priceUgx: Int,
    val periodLabel: String, // e.g. "per week", "per month", "per year"
    val features: List<String>,
    val isPopular: Boolean = false
) {
    val formattedPrice: String
        get() {
            val formatter = java.text.NumberFormat.getInstance(java.util.Locale.US)
            return "UGX ${formatter.format(priceUgx)}"
        }
}

enum class PaymentMethod(val displayName: String, val code: String) {
    MTN_MOBILE_MONEY("MTN Mobile Money", "MTN"),
    AIRTEL_MONEY("Airtel Money", "AIRTEL")
}
