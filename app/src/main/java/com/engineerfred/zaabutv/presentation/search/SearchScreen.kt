package com.engineerfred.zaabutv.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.engineerfred.zaabutv.domain.model.Vj
import com.engineerfred.zaabutv.presentation.components.EmptyState
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
fun SearchScreen(
    onMovieClick: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        ZaabuTopBar(
            title = "Search",
            showBackButton = true,
            onBackClick = onBackClick
        )

        // Search Input
        OutlinedTextField(
            value = state.query,
            onValueChange = viewModel::onQueryChanged,
            placeholder = {
                Text("Search movies, VJs...", color = TextSecondary, fontFamily = InterFamily)
            },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null, tint = ZaabuGold) },
            trailingIcon = {
                if (state.query.isNotEmpty()) {
                    IconButton(onClick = { viewModel.onQueryChanged("") }) {
                        Icon(Icons.Filled.Close, contentDescription = "Clear", tint = TextSecondary)
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(Dimens.RadiusMd),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = ZaabuGold,
                unfocusedBorderColor = DarkSurfaceVariant,
                cursorColor = ZaabuGold,
                focusedContainerColor = DarkSurface,
                unfocusedContainerColor = DarkSurface,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.ScreenPaddingHorizontal, vertical = 8.dp)
        )

        // VJ Filter Chips Row
        if (state.vjs.isNotEmpty()) {
            LazyRow(
                contentPadding = PaddingValues(horizontal = Dimens.ScreenPaddingHorizontal),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                // "All" chip
                item {
                    VjFilterChip(
                        label = "All VJs",
                        isSelected = state.selectedVjId == null,
                        photoUrl = null,
                        onClick = { viewModel.onVjFilterSelected(null) }
                    )
                }
                items(state.vjs, key = { it.id }) { vj ->
                    VjFilterChip(
                        label = vj.name,
                        isSelected = state.selectedVjId == vj.id,
                        photoUrl = vj.photoUrl,
                        onClick = { viewModel.onVjFilterSelected(vj.id) }
                    )
                }
            }
        }

        // Results
        if (state.results.isEmpty() && state.query.isNotBlank() && !state.isSearching) {
            EmptyState(
                icon = Icons.Filled.SearchOff,
                title = "No Results",
                message = "Try a different search term or VJ filter."
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
                items(state.results, key = { it.id }) { movie ->
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

@Composable
private fun VjFilterChip(
    label: String,
    isSelected: Boolean,
    photoUrl: String?,
    onClick: () -> Unit
) {
    val bgColor = if (isSelected) ZaabuGold else DarkSurfaceVariant
    val textColor = if (isSelected) Color.Black else Color.White

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(Dimens.RadiusFull))
            .background(bgColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        androidx.compose.foundation.layout.Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (photoUrl != null) {
                AsyncImage(
                    model = photoUrl,
                    contentDescription = label,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(22.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.padding(end = 6.dp))
            }
            Text(
                text = label,
                fontFamily = InterFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                color = textColor
            )
        }
    }
}
