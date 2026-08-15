package com.engineerfred.zaabutv.presentation.profile

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.engineerfred.zaabutv.presentation.components.LoadingState
import com.engineerfred.zaabutv.presentation.components.ZaabuTopBar
import com.engineerfred.zaabutv.ui.theme.DarkBackground
import com.engineerfred.zaabutv.ui.theme.DarkCard
import com.engineerfred.zaabutv.ui.theme.Dimens
import com.engineerfred.zaabutv.ui.theme.ErrorRed
import com.engineerfred.zaabutv.ui.theme.InterFamily
import com.engineerfred.zaabutv.ui.theme.OutfitFamily
import com.engineerfred.zaabutv.ui.theme.SuccessGreen
import com.engineerfred.zaabutv.ui.theme.TextSecondary
import com.engineerfred.zaabutv.ui.theme.ZaabuGold

@Composable
fun ProfileScreen(
    onNavigateToSubscription: () -> Unit,
    onNavigateToWatchlist: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(state.isLoggedOut) {
        if (state.isLoggedOut) {
            onLogout()
        }
    }

    if (state.isLoading) {
        LoadingState()
        return
    }

    val user = state.user

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState())
    ) {
        ZaabuTopBar(title = "Account & Profile")

        Spacer(modifier = Modifier.height(16.dp))

        // Profile Card
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.ScreenPaddingHorizontal),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(ZaabuGold),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = user?.name ?: "Guest User",
                fontFamily = OutfitFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color.White
            )

            Text(
                text = user?.email ?: "Sign in to activate full features",
                fontFamily = InterFamily,
                fontSize = 13.sp,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Subscription Pass Badge
            val isSubscribed = user?.isSubscribed == true
            val badgeText = if (isSubscribed) "${user?.activePlanName ?: "VIP Pass"} Active" else "No Active Pass | Tap to Subscribe"
            val badgeBg = if (isSubscribed) SuccessGreen.copy(alpha = 0.15f) else ErrorRed.copy(alpha = 0.15f)
            val badgeTextColor = if (isSubscribed) SuccessGreen else ErrorRed

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(Dimens.RadiusFull))
                    .background(badgeBg)
                    .clickable(onClick = onNavigateToSubscription)
                    .padding(horizontal = 14.dp, vertical = 6.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = null,
                        tint = badgeTextColor,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = badgeText,
                        fontFamily = OutfitFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = badgeTextColor
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Menu Section
        Column(
            modifier = Modifier.padding(horizontal = Dimens.ScreenPaddingHorizontal),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ProfileMenuItem(
                icon = Icons.Filled.Bookmark,
                title = "My Watchlist",
                onClick = onNavigateToWatchlist
            )
            ProfileMenuItem(
                icon = Icons.Filled.Star,
                title = "Subscription Plans",
                subtitle = if (user?.isSubscribed == true) "Pass active" else "Subscribe via Mobile Money",
                onClick = onNavigateToSubscription
            )
            ProfileMenuItem(
                icon = Icons.Filled.Phone,
                title = "Contact Support (WhatsApp)",
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/256754348118"))
                    context.startActivity(intent)
                }
            )
            ProfileMenuItem(
                icon = Icons.Filled.Gavel,
                title = "Legal Hub & Privacy",
                subtitle = "Terms, Privacy & Licensing",
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://zaabutv-legal-ph0k4r1k.agent.mira.tg"))
                    context.startActivity(intent)
                }
            )
            ProfileMenuItem(
                icon = Icons.Filled.Settings,
                title = "App Settings",
                onClick = onNavigateToSettings
            )
            ProfileMenuItem(
                icon = Icons.AutoMirrored.Filled.Logout,
                title = "Log Out",
                textColor = ErrorRed,
                onClick = viewModel::logout
            )
        }

        Spacer(modifier = Modifier.height(Dimens.BottomNavHeight + 32.dp))
    }
}

@Composable
private fun ProfileMenuItem(
    icon: ImageVector,
    title: String,
    subtitle: String? = null,
    textColor: Color = Color.White,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Dimens.RadiusMd))
            .background(DarkCard)
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (textColor == ErrorRed) ErrorRed else ZaabuGold,
            modifier = Modifier.size(22.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontFamily = OutfitFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = textColor
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    fontFamily = InterFamily,
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }
        }

        Icon(
            imageVector = Icons.Filled.ChevronRight,
            contentDescription = null,
            tint = TextSecondary,
            modifier = Modifier.size(18.dp)
        )
    }
}
