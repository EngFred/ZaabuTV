package com.engineerfred.zaabutv.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.engineerfred.zaabutv.presentation.components.ZaabuTopBar
import com.engineerfred.zaabutv.ui.theme.DarkBackground
import com.engineerfred.zaabutv.ui.theme.DarkCard
import com.engineerfred.zaabutv.ui.theme.DarkSurface
import com.engineerfred.zaabutv.ui.theme.Dimens
import com.engineerfred.zaabutv.ui.theme.InterFamily
import com.engineerfred.zaabutv.ui.theme.OutfitFamily
import com.engineerfred.zaabutv.ui.theme.TextSecondary
import com.engineerfred.zaabutv.ui.theme.ZaabuGold

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var cellularDownloads by remember { mutableStateOf(false) }
    var highQualityAudio by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState())
    ) {
        ZaabuTopBar(
            title = "Settings",
            showBackButton = true,
            onBackClick = onBackClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.padding(horizontal = Dimens.ScreenPaddingHorizontal)) {
            // Header
            Text(
                text = "STREAMING & DOWNLOADS",
                fontFamily = OutfitFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = TextSecondary
            )
            Spacer(modifier = Modifier.height(8.dp))

            SettingSwitchTile(
                title = "Download over Cellular Data",
                subtitle = "Allow downloads when not connected to Wi-Fi",
                checked = cellularDownloads,
                onCheckedChange = { cellularDownloads = it }
            )

            Spacer(modifier = Modifier.height(8.dp))

            SettingSwitchTile(
                title = "High Quality VJ Audio",
                subtitle = "Crisp, crystal-clear Luganda audio track narration",
                checked = highQualityAudio,
                onCheckedChange = { highQualityAudio = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "AUDIO & LANGUAGE",
                fontFamily = OutfitFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = TextSecondary
            )
            Spacer(modifier = Modifier.height(8.dp))

            SettingTextTile(
                title = "Default VJ Audio Language",
                value = "Luganda (Luganda Translation)"
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "ABOUT ZAABU TV",
                fontFamily = OutfitFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = TextSecondary
            )
            Spacer(modifier = Modifier.height(8.dp))

            SettingTextTile(title = "App Version", value = "v1.0.0-pitch-demo")
            Spacer(modifier = Modifier.height(8.dp))
            SettingTextTile(title = "Platform Concept", value = "Nollywood & Local Films with VJ Translation")
        }
    }
}

@Composable
private fun SettingSwitchTile(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Dimens.RadiusMd))
            .background(DarkCard)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontFamily = OutfitFamily, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.White)
            Text(text = subtitle, fontFamily = InterFamily, fontSize = 12.sp, color = TextSecondary)
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Black,
                checkedTrackColor = ZaabuGold,
                uncheckedThumbColor = TextSecondary,
                uncheckedTrackColor = DarkSurface
            )
        )
    }
}

@Composable
private fun SettingTextTile(
    title: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Dimens.RadiusMd))
            .background(DarkCard)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontFamily = OutfitFamily, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.White)
            Text(text = value, fontFamily = InterFamily, fontSize = 12.sp, color = ZaabuGold)
        }
    }
}
