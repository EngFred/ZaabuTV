package com.engineerfred.zaabutv.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object Splash : Screen

    @Serializable
    data object Onboarding : Screen

    @Serializable
    data object Login : Screen

    @Serializable
    data object Register : Screen

    @Serializable
    data object ForgotPassword : Screen

    @Serializable
    data object Home : Screen

    @Serializable
    data object Search : Screen

    @Serializable
    data class MovieDetail(val movieId: String) : Screen

    @Serializable
    data class Player(val movieId: String) : Screen

    @Serializable
    data object VjDirectory : Screen

    @Serializable
    data class VjProfile(val vjId: String) : Screen

    @Serializable
    data object Subscription : Screen

    @Serializable
    data class Checkout(val planId: String) : Screen

    @Serializable
    data object Profile : Screen

    @Serializable
    data object Watchlist : Screen

    @Serializable
    data object Settings : Screen
}
