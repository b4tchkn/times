package com.b4tchkn.times.ui.screen.weather

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun WeatherScreen(paddingValues: PaddingValues) {
    Box(
        modifier = Modifier
            .padding(top = paddingValues.calculateTopPadding())
            .fillMaxSize(),
    ) {
        Text(text = "Weather")
    }
}
