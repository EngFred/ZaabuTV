package com.engineerfred.zaabutv.presentation.components

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.engineerfred.zaabutv.ui.theme.DarkBackground
import com.engineerfred.zaabutv.ui.theme.DarkSurface
import com.engineerfred.zaabutv.ui.theme.Dimens
import com.engineerfred.zaabutv.ui.theme.InterFamily
import com.engineerfred.zaabutv.ui.theme.OutfitFamily
import com.engineerfred.zaabutv.ui.theme.TextSecondary
import com.engineerfred.zaabutv.ui.theme.ZaabuGold

@Composable
fun ZaabuTopBar(
    title: String? = null,
    showBackButton: Boolean = false,
    onBackClick: (() -> Unit)? = null,
    onSearchClick: (() -> Unit)? = null,
    onProfileClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(DarkBackground.copy(alpha = 0.95f))
            .padding(horizontal = Dimens.ScreenPaddingHorizontal),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (showBackButton && onBackClick != null) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
            }

            if (title != null) {
                Text(
                    text = title,
                    fontFamily = OutfitFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )
            } else {
                // Branded Logo "ZaabuTV"
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(ZaabuGold),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Z",
                            fontFamily = OutfitFamily,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "ZAABU",
                        fontFamily = OutfitFamily,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                        color = ZaabuGold,
                        letterSpacing = (-0.5).sp
                    )
                    Text(
                        text = "TV",
                        fontFamily = OutfitFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White,
                        letterSpacing = (-0.5).sp
                    )
                }
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            if (onSearchClick != null) {
                IconButton(onClick = onSearchClick) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search",
                        tint = Color.White
                    )
                }
            }
            if (onProfileClick != null) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(DarkSurface)
                        .clickable(onClick = onProfileClick),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Profile",
                        tint = ZaabuGold
                    )
                }
            }
        }
    }
}

@Composable
fun SectionHeader(
    title: String,
    modifier: Modifier = Modifier,
    onSeeAllClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.ScreenPaddingHorizontal, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontFamily = OutfitFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.White
        )
        if (onSeeAllClick != null) {
            Row(
                modifier = Modifier.clickable(onClick = onSeeAllClick),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "See All",
                    fontFamily = InterFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = ZaabuGold
                )
                Icon(
                    imageVector = Icons.Filled.ChevronRight,
                    contentDescription = "See All",
                    tint = ZaabuGold,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
fun EmptyState(
    icon: ImageVector,
    title: String,
    message: String,
    actionText: String? = null,
    onActionClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(DarkSurface),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = ZaabuGold,
                modifier = Modifier.size(36.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = title,
            fontFamily = OutfitFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = message,
            fontFamily = InterFamily,
            fontSize = 14.sp,
            color = TextSecondary,
            textAlign = TextAlign.Center
        )

        if (actionText != null && onActionClick != null) {
            Spacer(modifier = Modifier.height(24.dp))
            GoldButton(
                text = actionText,
                onClick = onActionClick,
                modifier = Modifier.width(200.dp)
            )
        }
    }
}

@Composable
fun LoadingState(
    modifier: Modifier = Modifier,
    message: String = "Loading ZaabuTV..."
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(
                color = ZaabuGold,
                strokeWidth = 3.dp,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = message,
                fontFamily = InterFamily,
                fontSize = 14.sp,
                color = TextSecondary
            )
        }
    }
}
