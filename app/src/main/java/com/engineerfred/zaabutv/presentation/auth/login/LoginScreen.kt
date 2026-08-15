package com.engineerfred.zaabutv.presentation.auth.login

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
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
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    var passwordVisible by remember { mutableStateOf(false) }

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            onLoginSuccess()
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
            Spacer(modifier = Modifier.height(48.dp))

            // Logo
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(ZaabuGold),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Z",
                    fontFamily = OutfitFamily,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 42.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Welcome Back",
                fontFamily = OutfitFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = Color.White
            )

            Text(
                text = "Sign in to continue watching VJ-translated movies",
                fontFamily = InterFamily,
                fontSize = 14.sp,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(36.dp))

            // Error alert
            if (state.error != null) {
                Text(
                    text = state.error!!,
                    fontFamily = InterFamily,
                    color = ErrorRed,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            // Email Field
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

            Spacer(modifier = Modifier.height(16.dp))

            // Password Field
            OutlinedTextField(
                value = state.pass,
                onValueChange = viewModel::onPasswordChanged,
                label = { Text("Password", color = TextSecondary) },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null, tint = ZaabuGold) },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = null,
                            tint = TextSecondary
                        )
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
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

            // Forgot Password
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onNavigateToForgotPassword) {
                    Text(
                        text = "Forgot Password?",
                        fontFamily = InterFamily,
                        fontSize = 13.sp,
                        color = ZaabuGold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Login Button
            GoldButton(
                text = "Sign In",
                onClick = viewModel::login,
                isLoading = state.isLoading
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Register Link
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Don't have an account? ",
                    fontFamily = InterFamily,
                    fontSize = 14.sp,
                    color = TextSecondary
                )
                Text(
                    text = "Register",
                    fontFamily = InterFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = ZaabuGold,
                    modifier = Modifier.clickable(onClick = onNavigateToRegister)
                )
            }

            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}
