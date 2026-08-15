package com.engineerfred.zaabutv.presentation.subscription

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.engineerfred.zaabutv.domain.model.PaymentMethod
import com.engineerfred.zaabutv.presentation.components.GoldButton
import com.engineerfred.zaabutv.presentation.components.LoadingState
import com.engineerfred.zaabutv.presentation.components.ZaabuTopBar
import com.engineerfred.zaabutv.ui.theme.AirtelRed
import com.engineerfred.zaabutv.ui.theme.DarkBackground
import com.engineerfred.zaabutv.ui.theme.DarkCard
import com.engineerfred.zaabutv.ui.theme.DarkSurface
import com.engineerfred.zaabutv.ui.theme.Dimens
import com.engineerfred.zaabutv.ui.theme.ErrorRed
import com.engineerfred.zaabutv.ui.theme.InterFamily
import com.engineerfred.zaabutv.ui.theme.MtnYellow
import com.engineerfred.zaabutv.ui.theme.OutfitFamily
import com.engineerfred.zaabutv.ui.theme.SuccessGreen
import com.engineerfred.zaabutv.ui.theme.TextSecondary
import com.engineerfred.zaabutv.ui.theme.ZaabuGold

@Composable
fun CheckoutScreen(
    onPaymentComplete: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CheckoutViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        ZaabuTopBar(
            title = "Mobile Money Checkout",
            showBackButton = true,
            onBackClick = onBackClick
        )

        val plan = state.plan

        if (plan == null) {
            LoadingState()
            return
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dimens.ScreenPaddingHorizontal),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            if (state.isSuccess) {
                // Success View
                Spacer(modifier = Modifier.height(32.dp))
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(SuccessGreen.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Filled.CheckCircle,
                        contentDescription = null,
                        tint = SuccessGreen,
                        modifier = Modifier.size(48.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Payment Successful!",
                    fontFamily = OutfitFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "You are now subscribed to the ${plan.name}. Enjoy unlimited VJ-translated movies!",
                    fontFamily = InterFamily,
                    fontSize = 14.sp,
                    color = TextSecondary,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(36.dp))

                GoldButton(
                    text = "Start Watching Now",
                    onClick = onPaymentComplete
                )
            } else {
                // Order Summary Card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(Dimens.RadiusMd))
                        .background(DarkCard)
                        .padding(16.dp)
                ) {
                    Column {
                        Text(
                            text = "ORDER SUMMARY",
                            fontFamily = OutfitFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = TextSecondary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = plan.name, fontFamily = OutfitFamily, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
                            Text(text = plan.formattedPrice, fontFamily = OutfitFamily, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = ZaabuGold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Provider Selection
                Text(
                    text = "Select Mobile Money Provider",
                    fontFamily = OutfitFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ProviderTile(
                        name = "MTN MoMo",
                        color = MtnYellow,
                        isSelected = state.provider == PaymentMethod.MTN_MOBILE_MONEY,
                        onSelect = { viewModel.onProviderSelected(PaymentMethod.MTN_MOBILE_MONEY) },
                        modifier = Modifier.weight(1f)
                    )
                    ProviderTile(
                        name = "Airtel Money",
                        color = AirtelRed,
                        isSelected = state.provider == PaymentMethod.AIRTEL_MONEY,
                        onSelect = { viewModel.onProviderSelected(PaymentMethod.AIRTEL_MONEY) },
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Phone Number Input
                Text(
                    text = "Mobile Money Phone Number",
                    fontFamily = OutfitFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (state.error != null) {
                    Text(
                        text = state.error!!,
                        fontFamily = InterFamily,
                        color = ErrorRed,
                        fontSize = 13.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                OutlinedTextField(
                    value = state.phoneNumber,
                    onValueChange = viewModel::onPhoneNumberChanged,
                    label = { Text("e.g. 0771234567", color = TextSecondary) },
                    leadingIcon = { Icon(Icons.Filled.Phone, contentDescription = null, tint = ZaabuGold) },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = ZaabuGold,
                        unfocusedBorderColor = DarkSurface,
                        cursorColor = ZaabuGold,
                        focusedContainerColor = DarkSurface,
                        unfocusedContainerColor = DarkSurface,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Pay Button
                GoldButton(
                    text = "Pay ${plan.formattedPrice}",
                    onClick = viewModel::processPayment,
                    isLoading = state.isLoading
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "🔒 Safe & secure prompt will appear on your phone",
                    fontFamily = InterFamily,
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }
        }
    }
}

@Composable
private fun ProviderTile(
    name: String,
    color: Color,
    isSelected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(Dimens.RadiusMd)
    Box(
        modifier = modifier
            .clip(shape)
            .background(DarkCard)
            .border(
                width = if (isSelected) 2.dp else 0.dp,
                color = if (isSelected) color else Color.Transparent,
                shape = shape
            )
            .clickable(onClick = onSelect)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(color)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = name,
                fontFamily = OutfitFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}
