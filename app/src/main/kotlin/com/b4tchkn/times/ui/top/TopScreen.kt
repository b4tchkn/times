package com.b4tchkn.times.ui.top

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.b4tchkn.times.ui.component.LoadIndicator

@Composable
fun TopScreen(
    modifier: Modifier,
    paddingValues: PaddingValues,
    topState: TopState,
    loading: Boolean,
) {
    Box(
        modifier = Modifier
            .padding(
                top = paddingValues.calculateTopPadding(),
            ),
    ) {
        Text(text = topState.news.articles.toString())
        if (loading) LoadIndicator()
    }
}
