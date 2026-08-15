package com.engineerfred.zaabutv.presentation.player

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.FastForward
import androidx.compose.material.icons.filled.FastRewind
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.engineerfred.zaabutv.data.mockdata.MockMovies
import com.engineerfred.zaabutv.data.mockdata.MockVjs
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
import kotlinx.coroutines.delay

@Composable
fun PlayerScreen(
    movieId: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val movie = remember(movieId) { MockMovies.getById(movieId) }
    val vj = remember(movie?.vjId) { movie?.vjId?.let { MockVjs.getById(it) } }
    val context = LocalContext.current

    var isPlaying by remember { mutableStateOf(true) }
    var progress by remember { mutableFloatStateOf(0.15f) }
    var showControls by remember { mutableStateOf(true) }
    var isInWatchlist by remember { mutableStateOf(false) }

    val movieTitle = movie?.title ?: "Movie Streaming"
    val vjName = movie?.vjName ?: vj?.name
    val backdropUrl = movie?.backdropUrl ?: "https://images.unsplash.com/photo-1536440136628-849c177e76a1?auto=format&fit=crop&q=80&w=1200"

    // Simulated playback progress
    LaunchedEffect(isPlaying) {
        while (isPlaying && progress < 1f) {
            delay(1000)
            progress = (progress + 0.003f).coerceAtMost(1f)
        }
    }

    // Auto-hide controls overlay inside video viewport
    LaunchedEffect(showControls) {
        if (showControls) {
            delay(4000)
            showControls = false
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState())
    ) {
        // Top App Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(DarkBackground)
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "NOW PLAYING",
                    fontFamily = OutfitFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    color = ZaabuGold
                )
                Text(
                    text = movieTitle,
                    fontFamily = OutfitFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
            IconButton(onClick = {
                android.widget.Toast.makeText(context, "Sharing movie link...", android.widget.Toast.LENGTH_SHORT).show()
            }) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = "Share",
                    tint = Color.White
                )
            }
        }

        // 🎬 16:9 Video Player Viewport Box (YouTube style centered container)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
                .background(Color.Black)
                .clickable { showControls = !showControls }
        ) {
            // Video Frame (16:9 Backdrop Image)
            AsyncImage(
                model = backdropUrl,
                contentDescription = movieTitle,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Video Controls Overlay (when tapped)
            if (showControls) {
                // Dark Scrim Overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.55f))
                )

                // Center Playback Buttons (Rewind - Play/Pause - Forward)
                Row(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalArrangement = Arrangement.spacedBy(28.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { progress = (progress - 0.05f).coerceAtLeast(0f) }) {
                        Icon(
                            imageVector = Icons.Filled.FastRewind,
                            contentDescription = "Rewind",
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(ZaabuGold)
                            .clickable { isPlaying = !isPlaying },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                            contentDescription = if (isPlaying) "Pause" else "Play",
                            tint = Color.Black,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    IconButton(onClick = { progress = (progress + 0.05f).coerceAtMost(1f) }) {
                        Icon(
                            imageVector = Icons.Filled.FastForward,
                            contentDescription = "Forward",
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }

                // Bottom Seekbar & Controls inside Player Box
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.9f))
                            )
                        )
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Slider(
                        value = progress,
                        onValueChange = { progress = it },
                        colors = SliderDefaults.colors(
                            thumbColor = ZaabuGold,
                            activeTrackColor = ZaabuGold,
                            inactiveTrackColor = Color.White.copy(alpha = 0.3f)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val totalMins = movie?.durationMinutes ?: 120
                        val elapsed = (progress * totalMins).toInt()
                        val totalHrs = totalMins / 60
                        val totalRemMins = totalMins % 60
                        val formattedTotal = if (totalHrs > 0) "${totalHrs}:${String.format("%02d", totalRemMins)}:00" else "${totalMins}:00"

                        Text(
                            text = "${elapsed / 60}:${String.format("%02d", elapsed % 60)} / $formattedTotal",
                            fontFamily = InterFamily,
                            fontSize = 11.sp,
                            color = Color.White.copy(alpha = 0.9f)
                        )

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick = { }, modifier = Modifier.size(32.dp)) {
                                Icon(
                                    imageVector = Icons.Filled.VolumeUp,
                                    contentDescription = "Volume",
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            IconButton(onClick = { }, modifier = Modifier.size(32.dp)) {
                                Icon(
                                    imageVector = Icons.Filled.Fullscreen,
                                    contentDescription = "Fullscreen",
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Details Section Below Video Player Box (YouTube / Streaming layout)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.ScreenPaddingHorizontal)
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            // Movie Title
            Text(
                text = movieTitle,
                fontFamily = OutfitFamily,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 22.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Metadata Badges (Rating, Year, Duration, Country)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = ZaabuGold,
                    modifier = Modifier.size(15.dp)
                )
                Text(
                    text = " ${movie?.rating ?: 4.8f}",
                    fontFamily = InterFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = ZaabuGold
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "${movie?.country?.flagEmoji} ${movie?.country?.displayName}",
                    fontFamily = InterFamily,
                    fontSize = 12.sp,
                    color = TextSecondary
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "${movie?.releaseYear} · ${movie?.durationFormatted}",
                    fontFamily = InterFamily,
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // VJ Translation Channel Tile
            if (vjName != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(Dimens.RadiusMd))
                        .background(VjBadgeColor.copy(alpha = 0.15f))
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = vj?.photoUrl ?: "https://images.unsplash.com/photo-1534528741775-53994a69daeb?auto=format&fit=crop&q=80&w=400",
                        contentDescription = vjName,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.Mic,
                                contentDescription = null,
                                tint = VjBadgeColor,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Luganda Narration",
                                fontFamily = InterFamily,
                                fontSize = 11.sp,
                                color = VjBadgeColor
                            )
                        }
                        Text(
                            text = vjName,
                            fontFamily = OutfitFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color.White
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(Dimens.RadiusFull))
                            .background(ZaabuGold)
                            .clickable {
                                android.widget.Toast.makeText(context, "Following $vjName", android.widget.Toast.LENGTH_SHORT).show()
                            }
                            .padding(horizontal = 14.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "Follow VJ",
                            fontFamily = OutfitFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            color = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            // Quick Action Buttons Row (Download, Save, Share)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                PlayerActionButton(
                    icon = Icons.Filled.Download,
                    label = "Download",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        android.widget.Toast.makeText(context, "Downloading $movieTitle for offline viewing...", android.widget.Toast.LENGTH_SHORT).show()
                    }
                )
                PlayerActionButton(
                    icon = if (isInWatchlist) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                    label = if (isInWatchlist) "Saved" else "Watchlist",
                    tint = if (isInWatchlist) ZaabuGold else Color.White,
                    modifier = Modifier.weight(1f),
                    onClick = {
                        isInWatchlist = !isInWatchlist
                        val msg = if (isInWatchlist) "Added to Watchlist" else "Removed from Watchlist"
                        android.widget.Toast.makeText(context, msg, android.widget.Toast.LENGTH_SHORT).show()
                    }
                )
                PlayerActionButton(
                    icon = Icons.Filled.Share,
                    label = "Share",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        android.widget.Toast.makeText(context, "Link copied to clipboard!", android.widget.Toast.LENGTH_SHORT).show()
                    }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Movie Synopsis
            Text(
                text = "Synopsis",
                fontFamily = OutfitFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = movie?.synopsis ?: "No synopsis available.",
                fontFamily = InterFamily,
                fontSize = 13.sp,
                color = TextSecondary,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun PlayerActionButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = Color.White
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(Dimens.RadiusMd))
            .background(DarkCard)
            .clickable(onClick = onClick)
            .padding(vertical = 10.dp, horizontal = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = tint,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = label,
            fontFamily = InterFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            color = Color.White
        )
    }
}
