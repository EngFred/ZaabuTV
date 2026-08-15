package com.engineerfred.zaabutv.presentation.moviedetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.PlayArrow
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.engineerfred.zaabutv.domain.model.Actor
import com.engineerfred.zaabutv.presentation.components.GoldButton
import com.engineerfred.zaabutv.presentation.components.LoadingState
import com.engineerfred.zaabutv.presentation.components.MovieCard
import com.engineerfred.zaabutv.presentation.components.SectionHeader
import com.engineerfred.zaabutv.presentation.components.ZaabuTopBar
import com.engineerfred.zaabutv.ui.theme.DarkBackground
import com.engineerfred.zaabutv.ui.theme.DarkCard
import com.engineerfred.zaabutv.ui.theme.DarkSurface
import com.engineerfred.zaabutv.ui.theme.DarkSurfaceVariant
import com.engineerfred.zaabutv.ui.theme.Dimens
import com.engineerfred.zaabutv.ui.theme.InterFamily
import com.engineerfred.zaabutv.ui.theme.OutfitFamily
import com.engineerfred.zaabutv.ui.theme.TextSecondary
import com.engineerfred.zaabutv.ui.theme.VjBadgeColor
import com.engineerfred.zaabutv.ui.theme.ZaabuGold

@Composable
fun MovieDetailScreen(
    onPlayClick: (String) -> Unit,
    onMovieClick: (String) -> Unit,
    onVjClick: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    if (state.isLoading) {
        LoadingState()
        return
    }

    val movie = state.movie ?: return

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState())
    ) {
        // Backdrop with overlay
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
        ) {
            AsyncImage(
                model = movie.backdropUrl,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Dark gradient overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.3f),
                                DarkBackground
                            ),
                            startY = 100f
                        )
                    )
            )

            // Back button
            ZaabuTopBar(
                showBackButton = true,
                onBackClick = onBackClick,
                modifier = Modifier.align(Alignment.TopStart)
            )

            // Play button center
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(ZaabuGold.copy(alpha = 0.9f))
                    .align(Alignment.Center)
                    .clickable { onPlayClick(movie.id) },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = "Play",
                    tint = Color.Black,
                    modifier = Modifier.size(36.dp)
                )
            }
        }

        Column(
            modifier = Modifier.padding(horizontal = Dimens.ScreenPaddingHorizontal)
        ) {
            // Title
            Text(
                text = movie.title,
                fontFamily = OutfitFamily,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 28.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Metadata Row
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = ZaabuGold,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = " ${String.format("%.1f", movie.rating)}",
                    fontFamily = InterFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = ZaabuGold
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "${movie.country.flagEmoji} ${movie.country.displayName}",
                    fontFamily = InterFamily,
                    fontSize = 13.sp,
                    color = TextSecondary
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "${movie.releaseYear} · ${movie.durationFormatted}",
                    fontFamily = InterFamily,
                    fontSize = 13.sp,
                    color = TextSecondary
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Genre Tags
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                movie.genres.forEach { genre ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(Dimens.RadiusFull))
                            .background(DarkSurfaceVariant)
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = genre,
                            fontFamily = InterFamily,
                            fontSize = 11.sp,
                            color = TextSecondary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // VJ Translation Badge
            if (state.vj != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(Dimens.RadiusMd))
                        .background(VjBadgeColor.copy(alpha = 0.15f))
                        .clickable { onVjClick(state.vj!!.id) }
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = state.vj!!.photoUrl,
                        contentDescription = state.vj!!.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Filled.Mic,
                                contentDescription = null,
                                tint = VjBadgeColor,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "VJ Translation",
                                fontFamily = InterFamily,
                                fontSize = 11.sp,
                                color = VjBadgeColor
                            )
                        }
                        Text(
                            text = state.vj!!.name,
                            fontFamily = OutfitFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                GoldButton(
                    text = "Play Movie",
                    onClick = { onPlayClick(movie.id) },
                    icon = Icons.Filled.PlayArrow,
                    modifier = Modifier.weight(1f)
                )
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(Dimens.RadiusMd))
                        .background(DarkSurface)
                        .clickable { viewModel.toggleWatchlist() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (state.isInWatchlist) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                        contentDescription = "Watchlist",
                        tint = if (state.isInWatchlist) ZaabuGold else TextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Synopsis
            Text(
                text = "Synopsis",
                fontFamily = OutfitFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = movie.synopsis,
                fontFamily = InterFamily,
                fontSize = 14.sp,
                color = TextSecondary,
                lineHeight = 22.sp
            )

            // Cast
            if (state.cast.isNotEmpty()) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Cast",
                    fontFamily = OutfitFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        // Cast Row (full width)
        if (state.cast.isNotEmpty()) {
            LazyRow(
                contentPadding = PaddingValues(horizontal = Dimens.ScreenPaddingHorizontal),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(state.cast, key = { it.id }) { actor ->
                    CastItem(actor = actor)
                }
            }
        }

        // Similar Movies
        if (state.similarMovies.isNotEmpty()) {
            Spacer(modifier = Modifier.height(20.dp))
            SectionHeader(title = "Similar Movies")
            LazyRow(
                contentPadding = PaddingValues(horizontal = Dimens.ScreenPaddingHorizontal),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(state.similarMovies, key = { it.id }) { movie ->
                    MovieCard(
                        movie = movie,
                        onMovieClick = onMovieClick
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun CastItem(actor: Actor) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(80.dp)
    ) {
        AsyncImage(
            model = actor.photoUrl,
            contentDescription = actor.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(DarkCard)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = actor.name,
            fontFamily = InterFamily,
            fontSize = 11.sp,
            color = Color.White,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 14.sp
        )
    }
}
