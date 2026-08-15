package com.engineerfred.zaabutv.domain.model

data class Vj(
    val id: String,
    val name: String,
    val photoUrl: String,
    val bio: String,
    val movieCount: Int,
    val specialties: List<String> = listOf("Action", "Drama", "Nollywood Translation")
)
