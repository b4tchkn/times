package com.b4tchkn.times.ui.top

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.b4tchkn.times.ui.component.AppDivider
import com.b4tchkn.times.ui.component.Gap
import com.b4tchkn.times.ui.top.component.TopArticleHeader
import com.b4tchkn.times.ui.top.component.TopArticleItem
import com.b4tchkn.times.ui.top.component.TopHeaderOverRow
import com.b4tchkn.times.ui.top.component.headlines_carousel.TopHeadlinesCarousel

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
                val article = topHeadlines?.firstOrNull() ?: return@item
                Box(
                    contentAlignment = Alignment.TopCenter
                ) {
                    TopArticleHeader(article = article) {
                        // TODO: handle callback
                    }
                    TopHeaderOverRow(
                        currentWeather = currentWeather,
                        onWeatherClicked = {
                            // TODO: handle callback
                        },
                        onSearchClicked = {
                            // TODO: handle callback
                        },
                    )
                }
            }
            item {
                Gap(32.dp)
            }
            if (topHeadlines != null) {
                item {
                    TopHeadlinesCarousel(
                        headlines = topHeadlines,
                        onArticleCardClicked = {
                            // TODO: handle callback
                        }
                    )
                }
                item {
                    Gap(padding = 16.dp)
                }
            }
            items(googleNewsArticles?.size ?: 0) { index ->
                val article = googleNewsArticles?.get(index) ?: return@items
                TopArticleItem(
                    modifier = Modifier.clickable {
                        // TODO: handle callback
                    },
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
