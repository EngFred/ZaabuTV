package com.engineerfred.zaabutv.presentation.home

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.engineerfred.zaabutv.domain.model.Movie
import com.engineerfred.zaabutv.presentation.components.LoadingState
import com.engineerfred.zaabutv.presentation.components.MovieCard
import com.engineerfred.zaabutv.presentation.components.SectionHeader
import com.engineerfred.zaabutv.presentation.components.ZaabuTopBar
import com.engineerfred.zaabutv.ui.theme.DarkBackground
import com.engineerfred.zaabutv.ui.theme.Dimens
import com.engineerfred.zaabutv.ui.theme.InterFamily
import com.engineerfred.zaabutv.ui.theme.OutfitFamily
import com.engineerfred.zaabutv.ui.theme.TextSecondary
import com.engineerfred.zaabutv.ui.theme.ZaabuGold
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    onMovieClick: (String) -> Unit,
    onSearchClick: () -> Unit,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    if (state.isLoading) {
        LoadingState()
        return
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState())
    ) {
        // Top Bar
        ZaabuTopBar(
            onSearchClick = onSearchClick,
            onProfileClick = onProfileClick
        )

        // Featured Hero Carousel
        if (state.featured.isNotEmpty()) {
            FeaturedCarousel(
                movies = state.featured,
                onMovieClick = onMovieClick
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // New Uploads
        MovieSection(
            title = "New Uploads",
            movies = state.newUploads,
            onMovieClick = onMovieClick
        )

        // Nollywood VJ-Translated
        MovieSection(
            title = "Nollywood | VJ Translated",
            movies = state.nollywoodFilms,
            onMovieClick = onMovieClick
        )

        // Ghanaian VJ-Translated
        MovieSection(
            title = "Ghanaian | VJ Translated",
            movies = state.ghanianFilms,
            onMovieClick = onMovieClick
        )

        // Uganda Originals
        MovieSection(
            title = "Uganda Originals",
            movies = state.ugandanFilms,
            onMovieClick = onMovieClick
        )

        // Classics
        MovieSection(
            title = "Classics",
            movies = state.classics,
            onMovieClick = onMovieClick
        )

        Spacer(modifier = Modifier.height(Dimens.BottomNavHeight + 16.dp))
    }
}

@Composable
private fun FeaturedCarousel(
    movies: List<Movie>,
    onMovieClick: (String) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { movies.size })

    // Auto-scroll the carousel
    LaunchedEffect(key1 = pagerState.pageCount) {
        while (true) {
            delay(4000)
            val nextPage = (pagerState.currentPage + 1) % movies.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.FeaturedCarouselHeight)
        ) { page ->
            val movie = movies[page]
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onMovieClick(movie.id) }
            ) {
                // Backdrop Image
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
                                    Color.Transparent,
                                    DarkBackground.copy(alpha = 0.7f),
                                    DarkBackground
                                ),
                                startY = 100f
                            )
                        )
                )

                // Movie info overlay
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(horizontal = Dimens.ScreenPaddingHorizontal)
                        .padding(bottom = 16.dp)
                ) {
                    Text(
                        text = movie.title,
                        fontFamily = OutfitFamily,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 26.sp,
                        color = Color.White,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = movie.country.flagEmoji,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = movie.genres.take(2).joinToString(" · "),
                            fontFamily = InterFamily,
                            fontSize = 13.sp,
                            color = TextSecondary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "· ${movie.releaseYear} · ${movie.durationFormatted}",
                            fontFamily = InterFamily,
                            fontSize = 13.sp,
                            color = TextSecondary
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Play Button
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(ZaabuGold)
                                .clickable { onMovieClick(movie.id) },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.PlayArrow,
                                contentDescription = "Play",
                                tint = Color.Black,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Watch Now",
                                fontFamily = OutfitFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = Color.White
                            )
                            Text(
                                text = "⭐ ${String.format("%.1f", movie.rating)}",
                                fontFamily = InterFamily,
                                fontSize = 12.sp,
                                color = ZaabuGold
                            )
                        }
                    }
                }
            }
        }

        // Page Indicators
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(movies.size) { index ->
                val isSelected = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .padding(horizontal = 3.dp)
                        .size(width = if (isSelected) 20.dp else 6.dp, height = 6.dp)
                        .clip(CircleShape)
                        .background(if (isSelected) ZaabuGold else Color.Gray.copy(alpha = 0.4f))
                )
            }
        }
    }
}

@Composable
private fun MovieSection(
    title: String,
    movies: List<Movie>,
    onMovieClick: (String) -> Unit
) {
    if (movies.isEmpty()) return

    SectionHeader(title = title)

    LazyRow(
        contentPadding = PaddingValues(horizontal = Dimens.ScreenPaddingHorizontal),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(movies, key = { it.id }) { movie ->
            MovieCard(
                movie = movie,
                onMovieClick = onMovieClick
            )
        }
    }

    Spacer(modifier = Modifier.height(20.dp))
}
