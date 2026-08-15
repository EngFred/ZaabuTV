package com.engineerfred.zaabutv.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.engineerfred.zaabutv.ui.theme.DarkSurface
import com.engineerfred.zaabutv.ui.theme.DarkSurfaceVariant
import com.engineerfred.zaabutv.ui.theme.Dimens
import com.engineerfred.zaabutv.ui.theme.InterFamily
import com.engineerfred.zaabutv.ui.theme.TextSecondary
import com.engineerfred.zaabutv.ui.theme.ZaabuGold

data class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)

val bottomNavItems = listOf(
    BottomNavItem("Home", Icons.Filled.Home, Screen.Home),
    BottomNavItem("Search", Icons.Filled.Search, Screen.Search),
    BottomNavItem("VJs", Icons.Filled.Mic, Screen.VjDirectory),
    BottomNavItem("My List", Icons.Filled.Bookmark, Screen.Watchlist),
    BottomNavItem("Profile", Icons.Filled.Person, Screen.Profile)
)

@Composable
fun BottomNavBar(
    currentScreen: Screen,
    onNavigate: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(DarkSurface)
    ) {
        // Subtle top border divider for clean visual separation
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(DarkSurfaceVariant)
        )

        NavigationBar(
            modifier = Modifier
                .height(Dimens.BottomNavHeight)
                .padding(top = 4.dp),
            containerColor = DarkSurface,
            contentColor = Color.White
        ) {
            bottomNavItems.forEach { item ->
                val isSelected = currentScreen::class == item.screen::class

                NavigationBarItem(
                    selected = isSelected,
                    onClick = { onNavigate(item.screen) },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            tint = if (isSelected) ZaabuGold else TextSecondary
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            fontFamily = InterFamily,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 11.sp,
                            color = if (isSelected) ZaabuGold else TextSecondary
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = DarkSurface
                    )
                )
            }
        }
    }
}
