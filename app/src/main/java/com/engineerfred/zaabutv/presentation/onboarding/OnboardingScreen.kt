package com.engineerfred.zaabutv.presentation.onboarding

import androidx.compose.foundation.background
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import coil3.compose.AsyncImage
import com.engineerfred.zaabutv.presentation.components.GoldButton
import com.engineerfred.zaabutv.ui.theme.DarkBackground
import com.engineerfred.zaabutv.ui.theme.Dimens
import com.engineerfred.zaabutv.ui.theme.InterFamily
import com.engineerfred.zaabutv.ui.theme.OutfitFamily
import com.engineerfred.zaabutv.ui.theme.TextSecondary
import com.engineerfred.zaabutv.ui.theme.ZaabuGold
import kotlinx.coroutines.launch

data class OnboardingSlide(
    val title: String,
    val description: String,
    val imageUrl: String
)

val onboardingSlides = listOf(
    OnboardingSlide(
        title = "Nollywood & Ghanaian Blockbusters",
        description = "Stream the best West African cinema with high drama, action, romance, and comedy all in one place.",
        imageUrl = "https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?auto=format&fit=crop&q=80&w=800"
    ),
    OnboardingSlide(
        title = "Translated by Uganda's Best VJs",
        description = "Experience movies narrated in rich Luganda by legend VJs including VJ SMK, VJ Jovan, VJ Kiwa, VJ Jingo, VJ Kevo and more!",
        imageUrl = "https://images.unsplash.com/photo-1511671782779-c97d3d27a1d4?auto=format&fit=crop&q=80&w=800"
    ),
    OnboardingSlide(
        title = "Original Ugandan Local Films",
        description = "Discover authentic home-grown Ugandan cinema straight from Kampala, Entebbe, and Wakaliwood.",
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ02gbuqt_NSXNWdt_D8_COX3g8MRpilrZ8m-V9kGxscpI2Rc3auVJk5Fc&s=10"
    )
)

@Composable
fun OnboardingScreen(
    onGetStartedClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { onboardingSlides.size })
    val scope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            val slide = onboardingSlides[page]
            Box(modifier = Modifier.fillMaxSize()) {
                // Background Image
                AsyncImage(
                    model = slide.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )

                // Scrim overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Black.copy(alpha = 0.4f),
                                    DarkBackground.copy(alpha = 0.85f),
                                    DarkBackground
                                )
                            )
                        )
                )

                // Slide Content
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = Dimens.ScreenPaddingHorizontal)
                        .padding(bottom = 140.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = slide.title,
                        fontFamily = OutfitFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = slide.description,
                        fontFamily = InterFamily,
                        fontSize = 15.sp,
                        color = TextSecondary,
                        textAlign = TextAlign.Center,
                        lineHeight = 22.sp
                    )
                }
            }
        }

        // Top Skip Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, end = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            if (pagerState.currentPage < onboardingSlides.size - 1) {
                TextButton(onClick = onGetStartedClick) {
                    Text(
                        text = "Skip",
                        fontFamily = InterFamily,
                        color = TextSecondary,
                        fontSize = 14.sp
                    )
                }
            }
        }

        // Bottom Navigation Controls (Dots & Button)
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = Dimens.ScreenPaddingHorizontal)
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Indicator Dots
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(onboardingSlides.size) { index ->
                    val isSelected = pagerState.currentPage == index
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(width = if (isSelected) 24.dp else 8.dp, height = 8.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) ZaabuGold else Color.Gray.copy(alpha = 0.5f))
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Action Button
            GoldButton(
                text = if (pagerState.currentPage == onboardingSlides.size - 1) "Get Started" else "Next",
                onClick = {
                    if (pagerState.currentPage < onboardingSlides.size - 1) {
                        scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                    } else {
                        onGetStartedClick()
                    }
                }
            )
        }
    }
}
