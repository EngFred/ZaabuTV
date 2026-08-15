package com.engineerfred.zaabutv.presentation.vj

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.engineerfred.zaabutv.domain.model.Vj
import com.engineerfred.zaabutv.presentation.components.LoadingState
import com.engineerfred.zaabutv.presentation.components.ZaabuTopBar
import com.engineerfred.zaabutv.ui.theme.DarkBackground
import com.engineerfred.zaabutv.ui.theme.DarkCard
import com.engineerfred.zaabutv.ui.theme.DarkSurfaceVariant
import com.engineerfred.zaabutv.ui.theme.Dimens
import com.engineerfred.zaabutv.ui.theme.InterFamily
import com.engineerfred.zaabutv.ui.theme.OutfitFamily
import com.engineerfred.zaabutv.ui.theme.TextSecondary
import com.engineerfred.zaabutv.ui.theme.VjBadgeColor
import com.engineerfred.zaabutv.ui.theme.ZaabuGold

@Composable
fun VjDirectoryScreen(
    onVjClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: VjDirectoryViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        ZaabuTopBar(title = "🎤 VJ Directory")

        Spacer(modifier = Modifier.height(8.dp))

        // Subtitle
        Text(
            text = "Uganda's best Video Jockeys — the voices behind your favourite movies",
            fontFamily = InterFamily,
            fontSize = 13.sp,
            color = TextSecondary,
            modifier = Modifier.padding(horizontal = Dimens.ScreenPaddingHorizontal)
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (state.isLoading) {
            LoadingState()
        } else {
            LazyColumn(
                contentPadding = PaddingValues(
                    horizontal = Dimens.ScreenPaddingHorizontal,
                    vertical = 8.dp
                ),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(state.vjs, key = { it.id }) { vj ->
                    VjListItem(vj = vj, onClick = { onVjClick(vj.id) })
                }

                // Bottom spacer for BottomNav
                item {
                    Spacer(modifier = Modifier.height(Dimens.BottomNavHeight + 16.dp))
                }
            }
        }
    }
}

@Composable
private fun VjListItem(
    vj: Vj,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Dimens.RadiusMd))
            .background(DarkCard)
            .clickable(onClick = onClick)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // VJ Photo
        AsyncImage(
            model = vj.photoUrl,
            contentDescription = vj.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(Dimens.VjAvatarSize)
                .clip(CircleShape)
                .background(DarkSurfaceVariant)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Filled.Mic,
                    contentDescription = null,
                    tint = VjBadgeColor,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = vj.name,
                    fontFamily = OutfitFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = vj.bio,
                fontFamily = InterFamily,
                fontSize = 12.sp,
                color = TextSecondary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Filled.Movie,
                    contentDescription = null,
                    tint = ZaabuGold,
                    modifier = Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${vj.movieCount} movies translated",
                    fontFamily = InterFamily,
                    fontSize = 11.sp,
                    color = ZaabuGold
                )
            }
        }

        Icon(
            imageVector = Icons.Filled.ChevronRight,
            contentDescription = null,
            tint = TextSecondary,
            modifier = Modifier.size(20.dp)
        )
    }
}
