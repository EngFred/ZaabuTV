package com.engineerfred.zaabutv.presentation.subscription

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.engineerfred.zaabutv.domain.model.SubscriptionPlan
import com.engineerfred.zaabutv.presentation.components.GoldButton
import com.engineerfred.zaabutv.presentation.components.LoadingState
import com.engineerfred.zaabutv.presentation.components.ZaabuTopBar
import com.engineerfred.zaabutv.ui.theme.DarkBackground
import com.engineerfred.zaabutv.ui.theme.DarkCard
import com.engineerfred.zaabutv.ui.theme.Dimens
import com.engineerfred.zaabutv.ui.theme.InterFamily
import com.engineerfred.zaabutv.ui.theme.OutfitFamily
import com.engineerfred.zaabutv.ui.theme.TextSecondary
import com.engineerfred.zaabutv.ui.theme.ZaabuGold

@Composable
fun SubscriptionScreen(
    onSelectPlan: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SubscriptionViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        ZaabuTopBar(
            title = "Subscription Plans",
            showBackButton = true,
            onBackClick = onBackClick
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Choose a plan to unlock unlimited VJ-translated movies",
            fontFamily = InterFamily,
            fontSize = 14.sp,
            color = TextSecondary,
            modifier = Modifier.padding(horizontal = Dimens.ScreenPaddingHorizontal)
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (state.isLoading) {
            LoadingState()
        } else {
            LazyColumn(
                contentPadding = PaddingValues(
                    horizontal = Dimens.ScreenPaddingHorizontal,
                    vertical = 8.dp
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(state.plans, key = { it.id }) { plan ->
                    PlanCard(plan = plan, onSelect = { onSelectPlan(plan.id) })
                }
            }
        }
    }
}

@Composable
private fun PlanCard(
    plan: SubscriptionPlan,
    onSelect: () -> Unit
) {
    val borderColor = if (plan.isPopular) ZaabuGold else Color.Transparent
    val shape = RoundedCornerShape(Dimens.RadiusLg)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(DarkCard)
            .border(width = if (plan.isPopular) 2.dp else 0.dp, color = borderColor, shape = shape)
            .padding(20.dp)
    ) {
        Column {
            if (plan.isPopular) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(Dimens.RadiusFull))
                        .background(ZaabuGold)
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.Star, contentDescription = null, tint = Color.Black, modifier = Modifier.size(12.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "MOST POPULAR",
                            fontFamily = OutfitFamily,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 10.sp,
                            color = Color.Black
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = plan.name,
                    fontFamily = OutfitFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )

                Text(
                    text = plan.formattedPrice,
                    fontFamily = OutfitFamily,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 22.sp,
                    color = ZaabuGold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            plan.features.forEach { feature ->
                Row(
                    modifier = Modifier.padding(vertical = 3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.CheckCircle,
                        contentDescription = null,
                        tint = ZaabuGold,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = feature,
                        fontFamily = InterFamily,
                        fontSize = 13.sp,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            GoldButton(
                text = "Subscribe via Mobile Money",
                onClick = onSelect
            )
        }
    }
}
