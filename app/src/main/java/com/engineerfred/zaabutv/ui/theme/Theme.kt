package com.engineerfred.zaabutv.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * ZaabuTV Dark Color Scheme
 *
 * A cinematic, premium dark-only color scheme designed for a streaming platform.
 * No light theme — streaming apps are always dark for immersive viewing.
 * No dynamic colors — we want a consistent, branded experience across all devices.
 */
private val ZaabuDarkColorScheme = darkColorScheme(
    // Primary
    primary = ZaabuGold,
    onPrimary = OnGold,
    primaryContainer = ZaabuGoldDark,
    onPrimaryContainer = ZaabuGoldLight,

    // Secondary
    secondary = WarmAmber,
    onSecondary = OnAmber,
    secondaryContainer = WarmAmberDark,
    onSecondaryContainer = WarmAmberLight,

    // Tertiary
    tertiary = WineRed,
    onTertiary = Color.White,
    tertiaryContainer = WineRedDark,
    onTertiaryContainer = WineRedLight,

    // Background
    background = DarkBackground,
    onBackground = TextPrimary,

    // Surface
    surface = DarkSurface,
    onSurface = TextPrimary,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = TextSecondary,
    surfaceTint = ZaabuGold,

    // Inverse
    inverseSurface = TextPrimary,
    inverseOnSurface = DarkBackground,
    inversePrimary = ZaabuGoldDark,

    // Error
    error = ErrorRed,
    onError = Color.White,
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    // Outline
    outline = OutlineColor,
    outlineVariant = OutlineVariant,

    // Scrim
    scrim = Color.Black,

    // Surface containers for Material 3 elevation tones
    surfaceContainerLowest = Color(0xFF08080D),
    surfaceContainerLow = DarkSurface,
    surfaceContainer = DarkSurfaceVariant,
    surfaceContainerHigh = DarkSurfaceElevated,
    surfaceContainerHighest = Color(0xFF2E2E48),
)

@Composable
fun ZaabuTVTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = ZaabuDarkColorScheme

    // Set status bar and navigation bar to transparent for edge-to-edge
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = false
                isAppearanceLightNavigationBars = false
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = ZaabuTypography,
        shapes = ZaabuShapes,
        content = content
    )
}