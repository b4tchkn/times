package com.b4tchkn.times.ui.screen.weather

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun WeatherScreenConnector() {
    Scaffold(topBar = {
        TopAppBar {
            Text(text = "Weather")
        }
    }) {
        WeatherScreen(paddingValues = it)
    }
}
