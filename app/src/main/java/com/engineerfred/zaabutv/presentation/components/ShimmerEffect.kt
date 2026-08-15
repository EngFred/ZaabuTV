package com.engineerfred.zaabutv.presentation.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.engineerfred.zaabutv.ui.theme.ShimmerBase
import com.engineerfred.zaabutv.ui.theme.ShimmerHighlight

@Composable
fun ShimmerBox(
    modifier: Modifier = Modifier,
    shapeRadius: Dp = 8.dp
) {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1200,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmerTranslate"
    )

    val brush = Brush.linearGradient(
        colors = listOf(
            ShimmerBase,
            ShimmerHighlight,
            ShimmerBase
        ),
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    Box(
        modifier = modifier
            .background(
                brush = brush,
                shape = RoundedCornerShape(shapeRadius)
            )
    )
}
