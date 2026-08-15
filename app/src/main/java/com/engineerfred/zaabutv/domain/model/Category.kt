package com.engineerfred.zaabutv.domain.model

enum class Category(val displayName: String) {
    LATEST("Latest On ZaabuTV"),
    NEW_UPLOADS("New Uploads"),
    CLASSICS("Classics"),
    UGANDA("Ugandan Local"),
    NIGERIA("Nollywood (VJ)"),
    GHANA("Ghanaian (VJ)")
}

enum class Country(val displayName: String, val flagEmoji: String) {
    UGANDA("Uganda", "🇺🇬"),
    NIGERIA("Nigeria", "🇳🇬"),
    GHANA("Ghana", "🇬🇭")
}
