package com.b4tchkn.times.ui.top

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.b4tchkn.times.ui.LoadingStatus
import com.b4tchkn.times.ui.component.Gap
import com.b4tchkn.times.ui.top.component.TopArticleHeader
import com.b4tchkn.times.ui.top.component.TopHeaderOverRow
import com.b4tchkn.times.ui.top.component.category_topic.TopCategoryTopicNews
import com.b4tchkn.times.ui.top.component.headlines_carousel.TopHeadlinesCarousel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun TopScreen(
    paddingValues: PaddingValues,
    topState: TopState,
    onRefreshed: () -> Unit,
) {
    val googleCategoryTopics = topState.googleTopicNews
    val topHeadlines = topState.topHeadlines?.articles
    val currentWeather = topState.currentWeather
    val loadingStatus = topState.loadingStatus

    SwipeRefresh(
        state = rememberSwipeRefreshState(
            isRefreshing = loadingStatus is LoadingStatus.Refresh && loadingStatus.loading
        ),
        onRefresh = {
            onRefreshed()
        }
    ) {
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
                if (googleCategoryTopics.isNotEmpty()) {
                    item {
                        TopCategoryTopicNews(
                            googleCategoryTopicNews = googleCategoryTopics,
                            onArticleClicked = {
                                // TODO: handle callback
                            },
                        )
                    }
                    item {
                        Gap(padding = 16.dp)
                    }
                }
            }
        }
    }
}
