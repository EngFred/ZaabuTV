package com.engineerfred.zaabutv.presentation.auth.register

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material3.Icon
import com.engineerfred.zaabutv.presentation.components.GoldButton
import com.engineerfred.zaabutv.ui.theme.DarkBackground
import com.engineerfred.zaabutv.ui.theme.DarkSurface
import com.engineerfred.zaabutv.ui.theme.Dimens
import com.engineerfred.zaabutv.ui.theme.ErrorRed
import com.engineerfred.zaabutv.ui.theme.InterFamily
import com.engineerfred.zaabutv.ui.theme.OutfitFamily
import com.engineerfred.zaabutv.ui.theme.TextSecondary
import com.engineerfred.zaabutv.ui.theme.ZaabuGold

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            onRegisterSuccess()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dimens.ScreenPaddingHorizontal)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(36.dp))

            // Logo
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(ZaabuGold),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Z",
                    fontFamily = OutfitFamily,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 38.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Create Account",
                fontFamily = OutfitFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = Color.White
            )

            Text(
                text = "Join ZaabuTV to watch Nollywood & local movies",
                fontFamily = InterFamily,
                fontSize = 14.sp,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(28.dp))

            if (state.error != null) {
                Text(
                    text = state.error!!,
                    fontFamily = InterFamily,
                    color = ErrorRed,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            // Name
            OutlinedTextField(
                value = state.name,
                onValueChange = viewModel::onNameChanged,
                label = { Text("Full Name", color = TextSecondary) },
                leadingIcon = { Icon(Icons.Filled.Person, contentDescription = null, tint = ZaabuGold) },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = ZaabuGold,
                    unfocusedBorderColor = DarkSurface,
                    focusedLabelColor = ZaabuGold,
                    cursorColor = ZaabuGold,
                    focusedContainerColor = DarkSurface,
                    unfocusedContainerColor = DarkSurface,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Email
            OutlinedTextField(
                value = state.email,
                onValueChange = viewModel::onEmailChanged,
                label = { Text("Email Address", color = TextSecondary) },
                leadingIcon = { Icon(Icons.Filled.Email, contentDescription = null, tint = ZaabuGold) },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = ZaabuGold,
                    unfocusedBorderColor = DarkSurface,
                    focusedLabelColor = ZaabuGold,
                    cursorColor = ZaabuGold,
                    focusedContainerColor = DarkSurface,
                    unfocusedContainerColor = DarkSurface,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Password
            OutlinedTextField(
                value = state.pass,
                onValueChange = viewModel::onPasswordChanged,
                label = { Text("Password", color = TextSecondary) },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null, tint = ZaabuGold) },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = ZaabuGold,
                    unfocusedBorderColor = DarkSurface,
                    focusedLabelColor = ZaabuGold,
                    cursorColor = ZaabuGold,
                    focusedContainerColor = DarkSurface,
                    unfocusedContainerColor = DarkSurface,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Confirm Password
            OutlinedTextField(
                value = state.confirmPass,
                onValueChange = viewModel::onConfirmPasswordChanged,
                label = { Text("Confirm Password", color = TextSecondary) },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null, tint = ZaabuGold) },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = ZaabuGold,
                    unfocusedBorderColor = DarkSurface,
                    focusedLabelColor = ZaabuGold,
                    cursorColor = ZaabuGold,
                    focusedContainerColor = DarkSurface,
                    unfocusedContainerColor = DarkSurface,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            GoldButton(
                text = "Create Account",
                onClick = viewModel::register,
                isLoading = state.isLoading
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Already have an account? ",
                    fontFamily = InterFamily,
                    fontSize = 14.sp,
                    color = TextSecondary
                )
                Text(
                    text = "Sign In",
                    fontFamily = InterFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = ZaabuGold,
                    modifier = Modifier.clickable(onClick = onNavigateToLogin)
                )
            }

            Spacer(modifier = Modifier.height(36.dp))
        }
    }
}
