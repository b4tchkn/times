package com.b4tchkn.times.ui.home

import androidx.compose.foundation.layout.Column
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
    val news by viewModel.news.collectAsState()
    val newsTopHeadlines by viewModel.news.collectAsState()
    val googleNews by viewModel.googleNews.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchNewsEverything()
        viewModel.fetchNewsTopHeadlines()
        viewModel.fetchGoogleNews()
    }

    Column {
        Text(text = news?.articles?.first()?.title.toString())
        Text(text = newsTopHeadlines?.articles?.first()?.title.toString())
        Text(text = googleNews?.articles?.first()?.title.toString())
    }
}
