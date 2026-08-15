package com.engineerfred.zaabutv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.engineerfred.zaabutv.navigation.ZaabuNavGraph
import com.engineerfred.zaabutv.ui.theme.ZaabuTVTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZaabuTVTheme {
                ZaabuNavGraph()
            }
        }
    }
}