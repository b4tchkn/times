package com.b4tchkn.times.ui.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
) {
    val newsString by viewModel.news.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetch()
    }

    Text(text = newsString.toString())
}
