package com.b4tchkn.times.ui.screen.search

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun SearchScreenConnector() {
    Scaffold(topBar = {
        TopAppBar {
            Text(text = "Search")
        }
    }) {
        SearchScreen(paddingValues = it)
    }
}
