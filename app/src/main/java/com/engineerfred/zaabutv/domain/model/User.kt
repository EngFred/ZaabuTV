package com.engineerfred.zaabutv.domain.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val avatarUrl: String = "",
    val isSubscribed: Boolean = false,
    val activePlanName: String? = null
)
