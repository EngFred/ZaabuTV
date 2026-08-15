package com.engineerfred.zaabutv.domain.model

data class Actor(
    val id: String,
    val name: String,
    val photoUrl: String,
    val bio: String,
    val country: Country = Country.NIGERIA
)
