package com.b4tchkn.times.ui.screen.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchScreen(paddingValues: PaddingValues) {
    Box(
        modifier = Modifier
            .padding(top = paddingValues.calculateTopPadding())
            .fillMaxSize(),
    ) {
        Text(text = "Search")
    }
}
