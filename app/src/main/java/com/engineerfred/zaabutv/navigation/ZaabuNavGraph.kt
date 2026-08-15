package com.engineerfred.zaabutv.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.engineerfred.zaabutv.data.datastore.UserPreferencesRepository
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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class NavInitialState(
    val hasCompletedOnboarding: Boolean = false,
    val isLoggedIn: Boolean = false
)

@HiltViewModel
class NavGraphViewModel @Inject constructor(
    val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    val initialState: StateFlow<NavInitialState> = combine(
        userPreferencesRepository.hasCompletedOnboarding,
        userPreferencesRepository.isLoggedIn
    ) { completedOnboarding, loggedIn ->
        NavInitialState(
            hasCompletedOnboarding = completedOnboarding,
            isLoggedIn = loggedIn
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = NavInitialState()
    )

    fun markOnboardingCompleted() {
        viewModelScope.launch {
            userPreferencesRepository.setCompletedOnboarding(true)
        }
    }
}

@Composable
fun ZaabuNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: NavGraphViewModel = hiltViewModel()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val navState by viewModel.initialState.collectAsState()

    // Determine current top-level screen for BottomNavBar
    val currentScreen: Screen = when {
        currentRoute?.contains("Home") == true -> Screen.Home
        currentRoute?.contains("Search") == true -> Screen.Search
        currentRoute?.contains("VjDirectory") == true -> Screen.VjDirectory
        currentRoute?.contains("Watchlist") == true -> Screen.Watchlist
        currentRoute?.contains("Profile") == true && !currentRoute.contains("VjProfile") -> Screen.Profile
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
                            popUpTo(Screen.Home) {
                                saveState = true
                            }
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
                        val targetScreen: Screen = when {
                            !navState.hasCompletedOnboarding -> Screen.Onboarding
                            !navState.isLoggedIn -> Screen.Login
                            else -> Screen.Home
                        }
                        navController.navigate(targetScreen) {
                            popUpTo(Screen.Splash) { inclusive = true }
                        }
                    }
                )
            }

            // Onboarding
            composable<Screen.Onboarding> {
                OnboardingScreen(
                    onGetStartedClick = {
                        viewModel.markOnboardingCompleted()
                        navController.navigate(Screen.Login) {
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
                    onRequireSubscription = { navController.navigate(Screen.Subscription) },
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
