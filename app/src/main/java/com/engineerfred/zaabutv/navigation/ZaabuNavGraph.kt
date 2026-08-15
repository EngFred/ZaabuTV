package com.engineerfred.zaabutv.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.engineerfred.zaabutv.presentation.auth.forgotpassword.ForgotPasswordScreen
import com.engineerfred.zaabutv.presentation.auth.login.LoginScreen
import com.engineerfred.zaabutv.presentation.auth.register.RegisterScreen
import com.engineerfred.zaabutv.presentation.home.HomeScreen
import com.engineerfred.zaabutv.presentation.moviedetail.MovieDetailScreen
import com.engineerfred.zaabutv.presentation.onboarding.OnboardingScreen
import com.engineerfred.zaabutv.presentation.player.PlayerScreen
import com.engineerfred.zaabutv.presentation.profile.ProfileScreen
import com.engineerfred.zaabutv.presentation.search.SearchScreen
import com.engineerfred.zaabutv.presentation.settings.SettingsScreen
import com.engineerfred.zaabutv.presentation.splash.SplashScreen
import com.engineerfred.zaabutv.presentation.subscription.CheckoutScreen
import com.engineerfred.zaabutv.presentation.subscription.SubscriptionScreen
import com.engineerfred.zaabutv.presentation.vj.VjDirectoryScreen
import com.engineerfred.zaabutv.presentation.vj.VjProfileScreen
import com.engineerfred.zaabutv.presentation.watchlist.WatchlistScreen
import com.engineerfred.zaabutv.ui.theme.DarkBackground

@Composable
fun ZaabuNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Determine current top-level screen for BottomNavBar
    val currentScreen: Screen = when {
        currentRoute?.contains("Home") == true -> Screen.Home
        currentRoute?.contains("Search") == true -> Screen.Search
        currentRoute?.contains("VjDirectory") == true -> Screen.VjDirectory
        currentRoute?.contains("Watchlist") == true -> Screen.Watchlist
        currentRoute?.contains("Profile") == true -> Screen.Profile
        else -> Screen.Home
    }

    // Show BottomNavBar only on main top-level destinations
    val showBottomBar = currentRoute?.let { route ->
        route.contains("Home") || route.contains("Search") ||
                route.contains("VjDirectory") || route.contains("Watchlist") ||
                (route.contains("Profile") && !route.contains("VjProfile"))
    } ?: false

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = DarkBackground,
        bottomBar = {
            if (showBottomBar) {
                BottomNavBar(
                    currentScreen = currentScreen,
                    onNavigate = { destination ->
                        navController.navigate(destination) {
                            popUpTo(Screen.Home) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash,
            modifier = Modifier.padding(innerPadding)
        ) {
            // Splash
            composable<Screen.Splash> {
                SplashScreen(
                    onSplashFinished = {
                        navController.navigate(Screen.Onboarding) {
                            popUpTo(Screen.Splash) { inclusive = true }
                        }
                    }
                )
            }

            // Onboarding
            composable<Screen.Onboarding> {
                OnboardingScreen(
                    onGetStartedClick = {
                        navController.navigate(Screen.Home) {
                            popUpTo(Screen.Onboarding) { inclusive = true }
                        }
                    }
                )
            }

            // Auth
            composable<Screen.Login> {
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate(Screen.Home) {
                            popUpTo(Screen.Login) { inclusive = true }
                        }
                    },
                    onNavigateToRegister = { navController.navigate(Screen.Register) },
                    onNavigateToForgotPassword = { navController.navigate(Screen.ForgotPassword) }
                )
            }

            composable<Screen.Register> {
                RegisterScreen(
                    onRegisterSuccess = {
                        navController.navigate(Screen.Home) {
                            popUpTo(Screen.Register) { inclusive = true }
                        }
                    },
                    onNavigateToLogin = { navController.navigate(Screen.Login) }
                )
            }

            composable<Screen.ForgotPassword> {
                ForgotPasswordScreen(
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            // Home
            composable<Screen.Home> {
                HomeScreen(
                    onMovieClick = { movieId -> navController.navigate(Screen.MovieDetail(movieId)) },
                    onSearchClick = { navController.navigate(Screen.Search) },
                    onProfileClick = { navController.navigate(Screen.Profile) }
                )
            }

            // Search
            composable<Screen.Search> {
                SearchScreen(
                    onMovieClick = { movieId -> navController.navigate(Screen.MovieDetail(movieId)) },
                    onBackClick = { navController.popBackStack() }
                )
            }

            // Movie Detail
            composable<Screen.MovieDetail> { backStackEntry ->
                val route = backStackEntry.toRoute<Screen.MovieDetail>()
                MovieDetailScreen(
                    onPlayClick = { movieId -> navController.navigate(Screen.Player(movieId)) },
                    onMovieClick = { movieId -> navController.navigate(Screen.MovieDetail(movieId)) },
                    onVjClick = { vjId -> navController.navigate(Screen.VjProfile(vjId)) },
                    onBackClick = { navController.popBackStack() }
                )
            }

            // Player
            composable<Screen.Player> { backStackEntry ->
                val route = backStackEntry.toRoute<Screen.Player>()
                PlayerScreen(
                    movieId = route.movieId,
                    onBackClick = { navController.popBackStack() }
                )
            }

            // VJ Directory
            composable<Screen.VjDirectory> {
                VjDirectoryScreen(
                    onVjClick = { vjId -> navController.navigate(Screen.VjProfile(vjId)) }
                )
            }

            // VJ Profile
            composable<Screen.VjProfile> {
                VjProfileScreen(
                    onMovieClick = { movieId -> navController.navigate(Screen.MovieDetail(movieId)) },
                    onBackClick = { navController.popBackStack() }
                )
            }

            // Subscription
            composable<Screen.Subscription> {
                SubscriptionScreen(
                    onSelectPlan = { planId -> navController.navigate(Screen.Checkout(planId)) },
                    onBackClick = { navController.popBackStack() }
                )
            }

            // Checkout
            composable<Screen.Checkout> {
                CheckoutScreen(
                    onPaymentComplete = {
                        navController.navigate(Screen.Home) {
                            popUpTo(Screen.Subscription) { inclusive = true }
                        }
                    },
                    onBackClick = { navController.popBackStack() }
                )
            }

            // Watchlist
            composable<Screen.Watchlist> {
                WatchlistScreen(
                    onMovieClick = { movieId -> navController.navigate(Screen.MovieDetail(movieId)) },
                    onExploreClick = { navController.navigate(Screen.Home) }
                )
            }

            // Profile
            composable<Screen.Profile> {
                ProfileScreen(
                    onNavigateToSubscription = { navController.navigate(Screen.Subscription) },
                    onNavigateToWatchlist = { navController.navigate(Screen.Watchlist) },
                    onNavigateToSettings = { navController.navigate(Screen.Settings) },
                    onLogout = {
                        navController.navigate(Screen.Login) {
                            popUpTo(Screen.Home) { inclusive = true }
                        }
                    }
                )
            }

            // Settings
            composable<Screen.Settings> {
                SettingsScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}
