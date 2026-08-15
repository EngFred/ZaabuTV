package com.engineerfred.zaabutv.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.engineerfred.zaabutv.ui.theme.Dimens
import com.engineerfred.zaabutv.ui.theme.OnGold
import com.engineerfred.zaabutv.ui.theme.OutfitFamily
import com.engineerfred.zaabutv.ui.theme.PremiumGradientEnd
import com.engineerfred.zaabutv.ui.theme.PremiumGradientStart

@Composable
fun GoldButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    icon: ImageVector? = null,
    height: Dp = Dimens.ButtonHeightMd,
    gradientColors: List<Color> = listOf(PremiumGradientStart, PremiumGradientEnd)
) {
    val shape = RoundedCornerShape(Dimens.RadiusMd)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(shape)
            .background(
                brush = if (enabled) Brush.horizontalGradient(gradientColors)
                else Brush.horizontalGradient(listOf(Color.Gray, Color.DarkGray))
            )
            .clickable(
                enabled = enabled && !isLoading,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(color = Color.White),
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = OnGold,
                strokeWidth = 2.5.dp,
                modifier = Modifier.padding(8.dp)
            )
        } else {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = OnGold
                    )
                    Spacer(modifier = Modifier.width(Dimens.SpacingSm))
                }
                Text(
                    text = text,
                    fontFamily = OutfitFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = OnGold
                )
            }
        }
    }
}
