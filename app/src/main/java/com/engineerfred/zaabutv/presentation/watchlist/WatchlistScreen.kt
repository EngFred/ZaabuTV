package com.engineerfred.zaabutv.presentation.watchlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.engineerfred.zaabutv.presentation.components.EmptyState
import com.engineerfred.zaabutv.presentation.components.LoadingState
import com.engineerfred.zaabutv.presentation.components.MovieCard
import com.engineerfred.zaabutv.presentation.components.ZaabuTopBar
import com.engineerfred.zaabutv.ui.theme.DarkBackground
import com.engineerfred.zaabutv.ui.theme.Dimens

@Composable
fun WatchlistScreen(
    onMovieClick: (String) -> Unit,
    onExploreClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WatchlistViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        ZaabuTopBar(title = "My Watchlist")

        Spacer(modifier = Modifier.height(12.dp))

        if (state.isLoading) {
            LoadingState()
        } else if (state.movies.isEmpty()) {
            EmptyState(
                icon = Icons.Filled.BookmarkBorder,
                title = "Your Watchlist is Empty",
                message = "Save movies you want to watch later by tapping the bookmark icon.",
                actionText = "Explore Movies",
                onActionClick = onExploreClick
            )
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(
                    start = Dimens.ScreenPaddingHorizontal,
                    end = Dimens.ScreenPaddingHorizontal,
                    bottom = Dimens.BottomNavHeight + 16.dp
                ),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(state.movies, key = { it.id }) { movie ->
                    MovieCard(
                        movie = movie,
                        onMovieClick = onMovieClick,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
