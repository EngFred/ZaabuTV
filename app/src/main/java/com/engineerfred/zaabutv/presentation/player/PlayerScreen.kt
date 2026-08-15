package com.engineerfred.zaabutv.presentation.player

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FastForward
import androidx.compose.material.icons.filled.FastRewind
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.engineerfred.zaabutv.ui.theme.DarkBackground
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
    var isPlaying by remember { mutableStateOf(true) }
    var progress by remember { mutableFloatStateOf(0f) }
    var showControls by remember { mutableStateOf(true) }

    // Simulated playback progress
    LaunchedEffect(isPlaying) {
        while (isPlaying && progress < 1f) {
            delay(1000)
            progress = (progress + 0.005f).coerceAtMost(1f)
        }
    }

    // Auto-hide controls
    LaunchedEffect(showControls) {
        if (showControls) {
            delay(5000)
            showControls = false
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .clickable { showControls = !showControls }
    ) {
        // Mock video frame — using a backdrop as placeholder
        AsyncImage(
            model = "https://images.unsplash.com/photo-1536440136628-849c177e76a1?auto=format&fit=crop&q=80&w=1200",
            contentDescription = "Movie frame",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Controls Overlay
        if (showControls) {
            // Scrim
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.55f))
            )

            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, start = 8.dp, end = 16.dp),
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
                Column {
                    Text(
                        text = "Now Playing",
                        fontFamily = InterFamily,
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                    Text(
                        text = "Movie Demo",
                        fontFamily = OutfitFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }

            // Center Playback Controls
            Row(
                modifier = Modifier.align(Alignment.Center),
                horizontalArrangement = Arrangement.spacedBy(32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { progress = (progress - 0.05f).coerceAtLeast(0f) }) {
                    Icon(
                        imageVector = Icons.Filled.FastRewind,
                        contentDescription = "Rewind",
                        tint = Color.White,
                        modifier = Modifier.size(36.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(ZaabuGold)
                        .clickable { isPlaying = !isPlaying },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                        contentDescription = if (isPlaying) "Pause" else "Play",
                        tint = Color.Black,
                        modifier = Modifier.size(40.dp)
                    )
                }

                IconButton(onClick = { progress = (progress + 0.05f).coerceAtMost(1f) }) {
                    Icon(
                        imageVector = Icons.Filled.FastForward,
                        contentDescription = "Forward",
                        tint = Color.White,
                        modifier = Modifier.size(36.dp)
                    )
                }
            }

            // Bottom Controls
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f))
                        )
                    )
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                // VJ Attribution
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(VjBadgeColor.copy(alpha = 0.3f))
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Mic,
                        contentDescription = null,
                        tint = VjBadgeColor,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "VJ Translation Active",
                        fontFamily = InterFamily,
                        fontSize = 11.sp,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Progress Slider
                Slider(
                    value = progress,
                    onValueChange = { progress = it },
                    colors = SliderDefaults.colors(
                        thumbColor = ZaabuGold,
                        activeTrackColor = ZaabuGold,
                        inactiveTrackColor = Color.Gray.copy(alpha = 0.3f)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                // Time & Actions
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val elapsed = (progress * 120).toInt()
                    Text(
                        text = "${elapsed / 60}:${String.format("%02d", elapsed % 60)} / 2:00:00",
                        fontFamily = InterFamily,
                        fontSize = 12.sp,
                        color = TextSecondary
                    )

                    Row {
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Filled.VolumeUp,
                                contentDescription = "Volume",
                                tint = Color.White
                            )
                        }
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Filled.Fullscreen,
                                contentDescription = "Fullscreen",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}
