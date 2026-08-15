package com.engineerfred.zaabutv.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil3.compose.AsyncImage
import com.engineerfred.zaabutv.domain.model.Movie
import com.engineerfred.zaabutv.ui.theme.DarkCard
import com.engineerfred.zaabutv.ui.theme.Dimens
import com.engineerfred.zaabutv.ui.theme.InterFamily
import com.engineerfred.zaabutv.ui.theme.OutfitFamily
import com.engineerfred.zaabutv.ui.theme.TextSecondary

@Composable
fun MovieCard(
    movie: Movie,
    onMovieClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    vjName: String? = null
) {
    val shape = RoundedCornerShape(Dimens.RadiusMd)

    Column(
        modifier = modifier
            .width(Dimens.MovieCardWidth)
            .clickable { onMovieClick(movie.id) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(Dimens.MovieCardPosterRatio)
                .clip(shape)
                .background(DarkCard)
        ) {
            // Poster Image
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Bottom Gradient Overlay for readability
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.85f)
                            ),
                            startY = 100f
                        )
                    )
            )

            // Rating Badge (Top Right)
            RatingBadge(
                rating = movie.rating,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(6.dp)
            )

            // Flag & Year Tag (Bottom Left)
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = movie.country.flagEmoji,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${movie.releaseYear}",
                    fontFamily = InterFamily,
                    fontSize = 10.sp,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Movie Title
        Text(
            text = movie.title,
            fontFamily = OutfitFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        // VJ Translation Badge or Genre subtitle
        val subtitleText = movie.vjName ?: movie.genres.firstOrNull() ?: movie.country.displayName
        Text(
            text = if (movie.vjName != null) "${movie.vjName}" else subtitleText,
            fontFamily = InterFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            color = TextSecondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
