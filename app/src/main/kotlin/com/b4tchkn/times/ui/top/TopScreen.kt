package com.b4tchkn.times.ui.top

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.b4tchkn.times.R
import com.b4tchkn.times.ui.component.Gap
import com.b4tchkn.times.ui.theme.AppColor
import com.b4tchkn.times.ui.top.component.TopArticleHeader
import com.b4tchkn.times.ui.top.component.TopWeatherContent
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

    SwipeRefresh(
        state = rememberSwipeRefreshState(
            // Connector側でLoadingIndicator出すため
            isRefreshing = false
        ),
        onRefresh = onRefreshed,
    ) {
        Box(
            modifier = Modifier.padding(
                top = paddingValues.calculateTopPadding(),
            ),
        ) {
            LazyColumn {
                item {
                    val article = topHeadlines?.firstOrNull() ?: return@item
                    Box(
                        contentAlignment = Alignment.TopEnd
                    ) {
                        TopArticleHeader(article = article) {
                            // TODO: handle callback
                        }
                        Box(
                            modifier = Modifier.padding(top = 16.dp, end = 16.dp)
                        ) {
                            Icon(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(AppColor.Grey)
                                    .clickable {
                                        // TODO: handle callback
                                    }
                                    .padding(12.dp),
                                imageVector = Icons.Default.Search,
                                contentDescription = stringResource(id = R.string.semantics_search)
                            )
                        }
                    }
                }
                if (currentWeather != null) {
                    items(3) {
                        when (it) {
                            0 -> {
                                Gap(padding = 8.dp)
                            }
                            1 -> {
                                Box(
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                ) {
                                    TopWeatherContent(currentWeather = currentWeather) {
                                        // TODO: handle callback
                                    }
                                }
                            }
                            2 -> {
                                Gap(padding = 8.dp)
                            }
                        }
                    }
                }
                if (topHeadlines != null) {
                    item {
                        TopHeadlinesCarousel(headlines = topHeadlines, onArticleCardClicked = {
                            // TODO: handle callback
                        })
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
