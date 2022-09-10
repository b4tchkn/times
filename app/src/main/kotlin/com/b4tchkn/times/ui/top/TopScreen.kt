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

@Composable
fun TopScreen(
    paddingValues: PaddingValues,
    topState: TopState,
) {
    val googleNewsArticles = topState.googleNews?.articles
    val topHeadlines = topState.topHeadlines?.articles
    val currentWeather = topState.currentWeather
    Box(
        modifier = Modifier
            .padding(
                top = paddingValues.calculateTopPadding(),
            ),
    ) {
        LazyColumn {
            item {
                Text(text = currentWeather?.weather?.description.toString())
            }
            item {
                Gap(padding = 16.dp)
            }
            item {
                val article = topHeadlines?.firstOrNull() ?: return@item
                Column {
                    Text(text = article.title ?: "")
                    Text(text = article.content ?: "")
                    Text(text = article.description ?: "")
                    Text(text = article.urlToImage ?: "")
                    Text(text = article.publishedAt.toString())
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
                    publishDate = article.publishedAt.toString(),
                )
                AppDivider(
                    startIndent = 16.dp,
                    endIndent = 16.dp,
                    thickness = 2.dp,
                )
            }
        }
    }
}
