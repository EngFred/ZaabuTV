package com.engineerfred.zaabutv.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.engineerfred.zaabutv.ui.theme.Dimens
import com.engineerfred.zaabutv.ui.theme.InterFamily
import com.engineerfred.zaabutv.ui.theme.OnGold
import com.engineerfred.zaabutv.ui.theme.VjBadgeColor
import com.engineerfred.zaabutv.ui.theme.ZaabuGold

@Composable
fun RatingBadge(
    rating: Float,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(Dimens.RadiusXs))
            .background(Color.Black.copy(alpha = 0.75f))
            .padding(horizontal = 6.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = "Rating",
            tint = ZaabuGold,
            modifier = Modifier.padding(end = 2.dp)
        )
        Text(
            text = String.format("%.1f", rating),
            fontFamily = InterFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            color = Color.White
        )
    }
}

@Composable
fun VjBadge(
    vjName: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    val shape = RoundedCornerShape(Dimens.RadiusFull)
    Row(
        modifier = modifier
            .clip(shape)
            .background(VjBadgeColor)
            .then(
                if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier
            )
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.Mic,
            contentDescription = "VJ Translation",
            tint = Color.White,
            modifier = Modifier
                .padding(end = 4.dp)
        )
        Text(
            text = vjName,
            fontFamily = InterFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            color = Color.White
        )
    }
}
