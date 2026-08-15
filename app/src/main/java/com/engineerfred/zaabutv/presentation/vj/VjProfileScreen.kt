package com.engineerfred.zaabutv.presentation.vj

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.engineerfred.zaabutv.presentation.components.LoadingState
import com.engineerfred.zaabutv.presentation.components.MovieCard
import com.engineerfred.zaabutv.presentation.components.ZaabuTopBar
import com.engineerfred.zaabutv.ui.theme.DarkBackground
import com.engineerfred.zaabutv.ui.theme.DarkSurface
import com.engineerfred.zaabutv.ui.theme.DarkSurfaceVariant
import com.engineerfred.zaabutv.ui.theme.Dimens
import com.engineerfred.zaabutv.ui.theme.InterFamily
import com.engineerfred.zaabutv.ui.theme.OutfitFamily
import com.engineerfred.zaabutv.ui.theme.TextSecondary
import com.engineerfred.zaabutv.ui.theme.VjBadgeColor
import com.engineerfred.zaabutv.ui.theme.ZaabuGold

@Composable
fun VjProfileScreen(
    onMovieClick: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: VjProfileViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    if (state.isLoading) {
        LoadingState()
        return
    }

    val vj = state.vj ?: return

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        ZaabuTopBar(
            title = vj.name,
            showBackButton = true,
            onBackClick = onBackClick
        )

        // Profile Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.ScreenPaddingHorizontal),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            AsyncImage(
                model = vj.photoUrl,
                contentDescription = vj.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(Dimens.VjAvatarSizeLarge)
                    .clip(CircleShape)
                    .background(DarkSurfaceVariant)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.Mic, contentDescription = null, tint = VjBadgeColor, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = vj.name,
                    fontFamily = OutfitFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = vj.bio,
                fontFamily = InterFamily,
                fontSize = 14.sp,
                color = TextSecondary,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Stats Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(Dimens.RadiusMd))
                    .background(DarkSurface)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem(value = "${vj.movieCount}", label = "Movies")
                StatItem(value = vj.specialties.take(2).joinToString(", "), label = "Specialties")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Section title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.Movie, contentDescription = null, tint = ZaabuGold, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Translated Movies",
                    fontFamily = OutfitFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Movies Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(
                start = Dimens.ScreenPaddingHorizontal,
                end = Dimens.ScreenPaddingHorizontal,
                bottom = 32.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(state.translatedMovies, key = { it.id }) { movie ->
                MovieCard(
                    movie = movie,
                    onMovieClick = onMovieClick,
                    vjName = vj.name,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun StatItem(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            fontFamily = OutfitFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = ZaabuGold
        )
        Text(
            text = label,
            fontFamily = InterFamily,
            fontSize = 11.sp,
            color = TextSecondary
        )
    }
}
