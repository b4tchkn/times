package com.b4tchkn.times.ui.top

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.b4tchkn.times.ui.component.AppDivider
import com.b4tchkn.times.ui.component.Gap
import com.b4tchkn.times.ui.component.LoadIndicator

@Composable
fun TopScreen(
    modifier: Modifier,
    paddingValues: PaddingValues,
    topState: TopState,
    loading: Boolean,
) {
    val googleNewsArticles = topState.googleNews?.articles
    val topHeadlines = topState.topHeadlines?.articles
    Box(
        modifier = Modifier
            .padding(
                top = paddingValues.calculateTopPadding(),
            ),
    ) {
        LazyColumn {
            item {
                val article = topHeadlines?.firstOrNull() ?: return@item
                Column {
                    Text(text = article.title ?: "")
                    Text(text = article.content ?: "")
                    Text(text = article.description ?: "")
                    Text(text = article.urlToImage ?: "")
                }
            }
            item {
                Gap(16.dp)
            }
            items(googleNewsArticles?.size ?: 0) { index ->
                val article = googleNewsArticles?.get(index) ?: return@items
                TopArticleItem(
                    modifier = Modifier.clickable { },
                    title = article.title,
                    source = article.source,
                    publishDate = article.pubDate,
                )
                AppDivider(
                    startIndent = 16.dp,
                    endIndent = 16.dp,
                    thickness = 2.dp,
                )
            }
        }
        if (loading) LoadIndicator()
    }
}
